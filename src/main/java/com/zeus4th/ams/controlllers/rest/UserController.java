package com.zeus4th.ams.controlllers.rest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.zeus4th.ams.model.User;
import com.zeus4th.ams.repository.UserDetailsRepository;
import com.zeus4th.ams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = {"http://localhost:8080","https://portal-ams.herokuapp.com","http://localhost:8080","https://amsportalapp.herokuapp.com"},
        allowedHeaders ={"Access-Control-Allow-Origin", "Access-Control-Allow-Headers","Authorization", "Cache-Control", "Content-Type"},
        allowCredentials = "true")
@RequestMapping("/users")
public class UserController{

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserDetailsRepository userDeailsResposity;

    // To Display All Users This Method is Invoke

//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String name) {
//        try {
//            List<User> users = new ArrayList<User>();
//            if (name == null)
//                userRepository.findAll().forEach(users::add);
//            else
//                userRepository.findByUserNameEquals(name).forEach(users::add);
//            if (users.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(users, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserByUserId(@PathVariable("userId") String userId) {
        Optional<User> userData = userRepository.findByUserId(userId);
        if (userData.isPresent()) {
            System.out.println(userData.get());
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            System.out.println("There is No value present in database");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Only Applicable for staging URL
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
            String userId = UUID.randomUUID().toString();      // userId generation
            System.out.println("This is The New UUID "+userId);
            System.out.println("Date:  "+newdate);
            User _user = userRepository
                    .save(new User(userId,userName,name,email,password,phone,organizationEmail,newdate,newdate,
                            profileUrl,true, false));
            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") String userId, @RequestBody User user) {
        Optional<User> userData = userRepository.findByUserId(userId);
        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setUserId(user.getUserId());
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
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String userId) {
        try {
            userRepository.deleteById(userId);
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

//    @Override
//    public List<User> findAll() {
//        List<UserDetails> list = userDeailsResposity.findAll();
//
//    }
}