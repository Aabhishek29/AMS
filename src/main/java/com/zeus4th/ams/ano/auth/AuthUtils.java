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
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) {
        try {
            List<User> users = userRepository.findByUserNameEquals(username);
            System.out.println(users.get(0).getUserName());
            System.out.println(users.get(0).getPassword());
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else {
                if (password.equals(users.get(0).getPassword())){
                    Map<String, String> body = new HashMap<>();
                    body.put("status", "true");
                    body.put("superUser",String.valueOf(users.get(0).getSuperUser()));
                    body.put("authenticated",String.valueOf(users.get(0).getAuthenticated()));
                    return new ResponseEntity<>(body,HttpStatus.OK);
                }
                else{
                    Map<String, String> body = new HashMap<>();
                    body.put("status", "false");
                    return new ResponseEntity<>(body,HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
