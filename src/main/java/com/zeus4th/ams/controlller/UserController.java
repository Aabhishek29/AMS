package com.zeus4th.ams.controlller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.zeus4th.ams.model.User;
import com.zeus4th.ams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = {"http://localhost:8080","https://portal-ams.herokuapp.com","http://localhost:8080","https://amsportalapp.herokuapp.com"},
        allowedHeaders ={"Access-Control-Allow-Origin", "Access-Control-Allow-Headers","Authorization", "Cache-Control", "Content-Type"},
        allowCredentials = "true")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String name) {
        try {
            List<User> users = new ArrayList<User>();
            if (name == null)
                userRepository.findAll().forEach(users::add);
            else
                userRepository.findByUserNameEquals(name).forEach(users::add);
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        Optional<User> userData = userRepository.findById(id);
        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/users/auth")
    public ResponseEntity<Map<String, String>> getUserByUserName(@RequestParam("username") String username,
                                                        @RequestParam("password") String password) {
        try {
            List<User> users = userRepository.findByUserNameEquals(username);
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

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestParam ("userName") String userName,
                                           @RequestParam("name") String name, @RequestParam("email")String email,
                                           @RequestParam("password") String password,
                                           @RequestParam("phone") long phone,
                                           @RequestParam("organizationEmail") String organizationEmail,
                                           @RequestParam("profileUrl") String profileUrl) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            ZoneId zoneid1 = ZoneId.of("Asia/Kolkata");
            LocalDateTime now = LocalDateTime.now(zoneid1);
            String newdate = dtf.format(now);

        try {

            System.out.println("Date:  "+newdate);
            User _user = userRepository
                    .save(new User(userName,name,email,password,phone,organizationEmail,newdate,newdate,
                            profileUrl,true, false));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        Optional<User> userData = userRepository.findById(id);
        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setId(user.getId());
            _user.setUserName(user.getUserName());
            _user.setName(user.getName());
            _user.setEmail(user.getEmail());
            _user.setAuthenticated(user.getAuthenticated());
            _user.setPhone(user.getPhone());
            _user.setPassword(user.getPassword());
            _user.setCreatedAt(_user.getCreatedAt());
            _user.setUpdatedAt();
            _user.setProfileUrl(user.getProfileUrl());
            _user.setOrganizationEmail(user.getOrganizationEmail());
            _user.setSuperUser(user.getSuperUser());

            return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/users")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        try {
            userRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/users/authenticated")
    public ResponseEntity<List<User>> findByPublished() {
        try {
            List<User> users = userRepository.findByAuthenticated(true);
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}