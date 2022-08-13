package com.zeus4th.ams.controlller;


import com.zeus4th.ams.model.User;
import com.zeus4th.ams.model.UserDetails;
import com.zeus4th.ams.repository.UserDetailsRepository;
import com.zeus4th.ams.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserDetailsController {

    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/allusersdetails")
    public ResponseEntity<List<UserDetails>> getAllUsersDetails(@RequestParam(required = false) String sessionDeatilId){
        try{
            List<UserDetails> users = new ArrayList<UserDetails>();
            if(sessionDeatilId == null){
                userDetailsRepository.findAll().forEach(users::add);
            }else{
                userDetailsRepository.findUserDetailsBySessionListId(sessionDeatilId).forEach(users::add);
            }
            if(users.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/allusersdetails")
    public ResponseEntity<UserDetails> createUserDetails(
            @RequestParam(name = "appId",defaultValue = "ano",required = false) String appId,
            @RequestParam(name = "status",defaultValue = "working",required = false) String status,
            @RequestParam(name = "profileUrl",defaultValue = "vdsbv.jvb",required = false) String profileUrl,
            @RequestParam(name = "userId",
                defaultValue = "50dc32e0-069c-4930-a70e-a6ac72029525",
                    required = false
            ) String userId
    ){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        ZoneId zoneid1 = ZoneId.of("Asia/Kolkata");
        LocalDateTime now = LocalDateTime.now(zoneid1);
        String newdate = dtf.format(now);
        List _user = userRepository.findUserByUserId(userId);
        log.error(_user.get(0).toString());
        User finalUser = (User) _user.get(0);
        try{
            String sessionListId = UUID.randomUUID().toString();
            log.error("we reach here");
            UserDetails userDetails = userDetailsRepository.
                    save(new UserDetails(sessionListId,newdate,newdate,appId,status,profileUrl,false,finalUser));
            return new ResponseEntity<>(userDetails,HttpStatus.CREATED);
        }catch (Exception e){
            log.error("there is an error ",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
