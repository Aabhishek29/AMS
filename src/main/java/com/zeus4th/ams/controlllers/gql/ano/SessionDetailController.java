package com.zeus4th.ams.controlllers.gql.ano;

import com.zeus4th.ams.model.User;
import com.zeus4th.ams.model.UserDetails;
import com.zeus4th.ams.model.ano.models.Participants;
import com.zeus4th.ams.model.ano.models.SessionDetails;
import com.zeus4th.ams.model.ano.models.SessionUserDetails;
import com.zeus4th.ams.repository.UserDetailsRepository;
import com.zeus4th.ams.repository.UserRepository;
import com.zeus4th.ams.repository.ano.repository.ParticipantsRepository;
import com.zeus4th.ams.services.UserDetailsServices;
import com.zeus4th.ams.services.ano.SessionDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class SessionDetailController {
    @Autowired
    private SessionDetailsServices sessionDetailsServices;
    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ParticipantsRepository participantsRepository;

    @QueryMapping("sessionDetails")
    public List<SessionDetails> getAllSessions(
            @Argument String sessionId
    ){
        return sessionDetailsServices.getAllSessions(sessionId);
    }

    /*
        * Here creator and participants is userId of person which create
            new session with other people or group of peoples.
        * Here chatType defines that chat is normal peer-to-peer or group chat.
    */

    public SessionUserDetails addSessionUserDetails(List<UserDetails> userDetailsList,SessionDetails sessionDetails){
        SessionUserDetails sessionUserDetails = new SessionUserDetails();
        Number uuid = UUID.randomUUID().node();
        try {
            sessionUserDetails.setId(uuid);
            sessionUserDetails.setUserDetails(userDetailsList);
            sessionUserDetails.setSessionDetails(sessionDetails);
        }catch (Exception e){
            System.out.println("Exception in SessionUserDetails addSessionUserDetails "+e.getMessage());
            return null;
        }
        return sessionUserDetails;
    }

    @MutationMapping("addSessionDetails")
    public SessionDetails addSessionDetails(
            @Argument String chatType,
            @Argument String groupProfileUrl,
            @Argument String connectionType,
            @Argument String creator,
            @Argument String sessionListId,
            @Argument List<String> chatParticipants
    ){
        String sessionId = UUID.randomUUID().toString();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        ZoneId zoneid1 = ZoneId.of("Asia/Kolkata");
        LocalDateTime now = LocalDateTime.now(zoneid1);
        String createdAt = dtf.format(now);

        SessionDetails sessionDetails = new SessionDetails();

        try {
            sessionDetails.setSessionId(sessionId);
            sessionDetails.setCreatedAt(createdAt);
            sessionDetails.setUpdatedAt(createdAt);
            sessionDetails.setChatType(chatType);
            sessionDetails.setGroupProfileUrl(groupProfileUrl);
            sessionDetails.setConnectionType(connectionType);

           /*
              sessionUserDetails is like a connector between userDetails and SessionDetails
           */
            List<UserDetails> userDetailsList = new ArrayList<>();
            // adding creator of session in chat-participants table
            sessionDetails.setCreator(creator);
            // participants adding
            // here creator is basically userId of creator
            List<Participants> participantsList = new ArrayList<>();
            System.out.println("Error in line 98");
            Participants participants = new Participants();
            participants.setParticipantId(sessionId+creator);
            participants.setSessionId(sessionId);
            participants.setUserId(creator);
            participantsList.add(participantsRepository.save(participants));
            System.out.println("Error in line 104");
            UserDetails userDetails = userDetailsRepository.findBySessionListId(sessionListId).get(0);
            userDetailsList.add(userDetails);
            // adding participants in chat-participants table
            for (String chatParticipant : chatParticipants) {
                participants.setUserId(chatParticipant);
                participants.setSessionId(sessionId);
                participants.setParticipantId(sessionId + chatParticipant);
                participantsList.add(participantsRepository.save(participants));
                User user = userRepository.findUserByUserId(chatParticipant);
                userDetails = userDetailsRepository.findByUser(user).get(0);
                userDetailsList.add(userDetails);
            }
            sessionDetails.setParticipantsList(participantsList);
            SessionUserDetails sessionUserDetails = addSessionUserDetails(userDetailsList,sessionDetails);
            sessionDetails.setSessionUserDetails(sessionUserDetails);
            return sessionDetailsServices.createSession(sessionDetails);
        }catch (Exception e){
            System.out.println("addSessionDetails: getting exception on addSessionDetails "+e.getMessage());
            return null;
        }
    }

}
