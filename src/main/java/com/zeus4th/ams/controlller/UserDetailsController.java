package com.zeus4th.ams.controlller;


import com.zeus4th.ams.model.UserDetails;
import com.zeus4th.ams.repository.UserDetailsRepository;
import com.zeus4th.ams.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    @PostMapping("/allusersdetails" )
    public ResponseEntity<UserDetails> createUserDetails(
            @RequestParam("appId") String appId,
            @RequestParam("status") String status,
            @RequestParam("profileUrl" ) String profileUrl,
            @RequestParam("userId") String userId
    ){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        ZoneId zoneid1 = ZoneId.of("Asia/Kolkata");
        LocalDateTime now = LocalDateTime.now(zoneid1);
        String newdate = dtf.format(now);
        try{
            String sessionListId = UUID.randomUUID().toString();
            log.error("we reach here");
            UserDetails userDetails = userDetailsRepository.
                    save(new UserDetails(sessionListId,newdate,newdate,appId,status,profileUrl,false,userId));
            return new ResponseEntity<UserDetails>(userDetails,HttpStatus.CREATED);
        }catch (Exception e){
            log.error("there is an error ",e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
