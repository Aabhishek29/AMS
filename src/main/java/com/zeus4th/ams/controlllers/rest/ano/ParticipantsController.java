package com.zeus4th.ams.controlllers.rest.ano;

import com.zeus4th.ams.model.ano.models.Participants;
import com.zeus4th.ams.repository.ano.repository.ParticipantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Component
public class ParticipantsController {

    @Autowired
    private ParticipantsRepository participantsRepository;

    @GetMapping("allParticipants")
    public ResponseEntity<List<Participants>> getAllParticipants(
            @RequestParam(required = false) String sessionId
    ){
        List<Participants> participantsList = null;
        try {
            if(sessionId!=null){
                participantsRepository.findBySessionId(sessionId);
            }
            participantsList = participantsRepository.findAll();
            if(participantsList.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(participantsList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
