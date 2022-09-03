package com.zeus4th.ams.controlller.anocontroller;

import com.zeus4th.ams.model.UserDetails;
import com.zeus4th.ams.model.ano.models.Participants;
import com.zeus4th.ams.model.ano.models.SessionDetails;
import com.zeus4th.ams.repository.UserDetailsRepository;
import com.zeus4th.ams.repository.UserRepository;
import com.zeus4th.ams.repository.ano.repository.ParticipantsRepository;
import com.zeus4th.ams.repository.ano.repository.SessionDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class SessionDetailsController {

    @Autowired
    private SessionDetailsRepository sessionDetailsRepository;
    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private ParticipantsRepository participantsRepository;
    @Autowired
    private UserRepository userRepository;


    @GetMapping("allsessiondetails")
    public ResponseEntity<List<SessionDetails>> getAllSessionDetails(
            @RequestParam(required = false) String sessionId
    ){
        try{
            List<SessionDetails> sessionDetailsList = new ArrayList<>(sessionDetailsRepository.findAll());
            if(sessionDetailsList.isEmpty()){
                log.info("No Data found in database getAllSessionDetails");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(sessionDetailsList,HttpStatus.OK);
            }
        }catch (Exception e){
            log.error("There is some issue while fetching data in getAllSessionDetails:",e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("allsessiondetails")
    @ResponseBody
    public ResponseEntity<Object> createSessionDetails(
            @RequestParam("chatType") String chatType,
            @RequestParam("groupProfileUrl") String groupProfileUrl,
            @RequestParam("connectionType") String connectionType,
            @RequestParam("creator") String creatorId,
            @RequestParam("receiver") List<String> receiverId,
            @RequestParam("sessionListId") String sessionListId
    ){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        ZoneId zoneid1 = ZoneId.of("Asia/Kolkata");
        LocalDateTime now = LocalDateTime.now(zoneid1);
        String createdAt = dtf.format(now);

        try {

            String sessionId = UUID.randomUUID().toString();   // sessionId created

            // session Saved for creator in sessionDetails
            SessionDetails sessionDetails = sessionDetailsRepository.save(new SessionDetails(sessionId,createdAt,createdAt,chatType,groupProfileUrl,connectionType,creatorId,sessionListId));

            // participants added to participants list

            // sessionDetails need to pushed in UserDetails for both creator and receivers
            List<Participants> participantsList = new ArrayList<>();
            Participants participants = participantsRepository.save(new Participants(creatorId+sessionId,creatorId,sessionId));
            participantsList.add(participants);
            for (String userId : receiverId) {
                participants = participantsRepository.save(new Participants(userId+sessionId,userId, sessionId));
//                UserDetails = userDetailsRepository.findUserDetailsByUserAndAppId(userRepository.findUserByUserId(userId),"ano");
//                List<SessionDetails> sessionDetails1 = userDetails.getListSessions();
//                sessionDetails1.add(sessionDetails);
//                userDetails.setListSessions(sessionDetails1);
                participantsList.add(participants);
            }
            sessionDetails.setParticipantsList(participantsList);
            return new ResponseEntity<>(sessionDetails, HttpStatus.OK);
        }catch (Exception e){
            log.error("There is some issue with SessionDetails Controller::",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
