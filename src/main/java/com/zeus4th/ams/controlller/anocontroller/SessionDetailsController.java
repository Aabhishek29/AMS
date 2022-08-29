package com.zeus4th.ams.controlller.anocontroller;

import com.zeus4th.ams.model.UserDetails;
import com.zeus4th.ams.model.ano.models.Participants;
import com.zeus4th.ams.model.ano.models.SessionDetails;
import com.zeus4th.ams.repository.UserDetailsRepository;
import com.zeus4th.ams.repository.ano.repository.ParticipantsRepository;
import com.zeus4th.ams.repository.ano.repository.SessionDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("allsessiondetails")
    public ResponseEntity<List<SessionDetails>> getAllSessionDetails(
            @RequestParam(required = false) String chatMessageId
    ){
        try{
            List<SessionDetails> sessionDetailsList = new ArrayList<>();
            sessionDetailsRepository.findAll().forEach(sessionDetailsList::add);
            if(sessionDetailsList.isEmpty()){
                log.info("No Data found in database getAllSessionDetails");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                log.error(sessionDetailsList.get(0).getSessionId().toString());
                return new ResponseEntity<>(sessionDetailsList,HttpStatus.OK);
            }
        }catch (Exception e){
            log.error("There is some issue while fetching data in getAllSessionDetails:",e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("allsessiondetails")
    public ResponseEntity<SessionDetails> createSessionDetails(
            @RequestParam("chatType") String chatType,
            @RequestParam("groupProfileUrl") String groupProfileUrl,
            @RequestParam("connectionType") String connectionType,
            @RequestParam("creator") String creatorId,
            @RequestParam("receiver") List<String> receiverId,
            @RequestParam("sessionListId") String sessionListId
    ){
        log.error("Line 64");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        ZoneId zoneid1 = ZoneId.of("Asia/Kolkata");
        LocalDateTime now = LocalDateTime.now(zoneid1);
        String newdate = dtf.format(now);
        try {
            log.error("Line 70");
            String sessionId = UUID.randomUUID().toString();
            List<Participants> participantsList = new ArrayList<>();
            Participants participants = participantsRepository.save(new Participants(creatorId+sessionId,creatorId,sessionId));
            participantsList.add(participants);
            for (String s : receiverId) {
                participants = participantsRepository.save(new Participants(s+sessionId,s, sessionId));
                participantsList.add(participants);
            }
            UserDetails userDetails = userDetailsRepository.findBySessionListId(sessionListId).get(0);
            log.error("Line 73");
            SessionDetails sessionDetails = sessionDetailsRepository.save(new SessionDetails(sessionId,newdate,newdate,chatType,groupProfileUrl,connectionType,creatorId,participantsList,userDetails));
            log.error("Line 75");
            log.error(sessionDetails.toString());
            return new ResponseEntity<>(sessionDetails,HttpStatus.OK);
        }catch (Exception e){
            log.error("There is some issue with SessionDetails Controller::",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
