package com.zeus4th.ams.ano.auth;

import com.zeus4th.ams.model.User;
import com.zeus4th.ams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequestMapping("api/")
public class AuthUtils {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/auth")
    public ResponseEntity<Map<String, String>> getUserByUserName(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam("password") String password
    ) {
        try {
            System.out.println(String.format("API Call for getUserByUserName: Username is %s and Email is %s",username,email));
            List<User> users = null;
            if (email!=null){
                users = userRepository.findByEmail(email);
            }else if(username != null){
                System.out.println(String.format("API Call for getUserByUserName: %s",username));
                users = userRepository.findByUserNameEquals(username);
            }else {
                Map<String, String> body = new HashMap<>();
                body.put("email", "not valid");
                body.put("username", "not valid");
                return new ResponseEntity<>(body,HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }
            System.out.println(users.get(0).getUserName());
            System.out.println(users.get(0).getPassword());
            if (users.size()==0) {
                System.out.println("API Call for getUserByUserName: User Not Found");
                Map<String, String> body = new HashMap<>();
                body.put("Error message", "not valid credentials");
                return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
            }else {
                if (password.equals(users.get(0).getPassword())){
                    Map<String, String> body = new HashMap<>();
                    body.put("status", "true");
                    body.put("superUser",String.valueOf(users.get(0).getSuperUser()));
                    body.put("authenticated",String.valueOf(users.get(0).getAuthenticated()));
                    body.put("userId", String.valueOf(users.get(0).getUserId()));
                    body.put("name",String.valueOf(users.get(0).getName()));
                    body.put("username",String.valueOf(users.get(0).getUserName()));
                    return new ResponseEntity<>(body,HttpStatus.OK);
                }
                else{
                    System.out.println("API Call for getUserByUserName: Credentials Not Matched");
                    Map<String, String> body = new HashMap<>();
                    body.put("error message", "userId and password not matched");
                    body.put("status", "false");
                    return new ResponseEntity<>(body,HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            Map<String, String> body = new HashMap<>();
            body.put("Error message", "not valid credentials");
            System.out.println("API Call for getUserByUserName: Exception found:"+e.getMessage());
            return new ResponseEntity<>(body,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
