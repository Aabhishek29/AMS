package com.zeus4th.ams.controlllers.gql.ano;

import com.zeus4th.ams.model.User;
import com.zeus4th.ams.model.UserDetails;
import com.zeus4th.ams.model.ano.models.Participants;
import com.zeus4th.ams.model.ano.models.SessionDetails;
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
    private UserRepository userRepository;
    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private UserDetailsServices userDetailsServices;
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
        }catch (Exception e){
            System.out.println("addSessionDetails: getting exception on addSessionDetails "+e.getMessage());
            return null;
        }
        return sessionDetails;
    }

}
