package com.zeus4th.ams.controlller.anocontroller;

import com.zeus4th.ams.model.ano.models.ChatMessages;
import com.zeus4th.ams.repository.ano.repository.ChatMessageRepository;
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
public class ChatMessageControler {


    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @GetMapping("allchatmessages")
    public ResponseEntity<List<ChatMessages>> getAllChatMessages(
            @RequestParam(required = false) String chatMessageId
            ){
        try{
            List<ChatMessages> chatMessagesList = new ArrayList<>(chatMessageRepository.findAll());
            if(chatMessagesList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else {
                return new ResponseEntity<>(chatMessagesList,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("allchatmessages")
    public ResponseEntity<ChatMessages> createChatMessage(
            @RequestParam("message") String message,
            @RequestParam("mimeType") String mimeType,
            @RequestParam("sender") String sender,
            @RequestParam("emotion") String emotion,
            @RequestParam("sessionId") String sessionId
    ){
        String messageId = UUID.randomUUID().toString();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        ZoneId zoneid1 = ZoneId.of("Asia/Kolkata");
        LocalDateTime now = LocalDateTime.now(zoneid1);
        String createdAt = dtf.format(now);
        try{
            ChatMessages cm = new ChatMessages(messageId,message,mimeType,createdAt,sender,emotion,sessionId);
            chatMessageRepository.save(cm);
            return new ResponseEntity<>(cm,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
