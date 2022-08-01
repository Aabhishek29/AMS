package com.zeus4th.ams.controlller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.zeus4th.ams.model.User;
import com.zeus4th.ams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
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
    public ResponseEntity<List<User>> getUserByUserName(@RequestParam("username") String username,
                                                        @RequestParam("password") String password) {
        try {
            List<User> users = userRepository.findByUserNameEquals(username);
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else {
                if (password.equals(users.get(0).getPassword()))
                return new ResponseEntity<>(HttpStatus.OK);
                else
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser2(@RequestParam ("userName") String userName,
                                           @RequestParam("name") String name, @RequestParam("email")String email,
                                           @RequestParam("password") String password,
                                           @RequestParam("phone") long phone,
                                           @RequestParam("organizationEmail") String organizationEmail,
                                           @RequestParam("profileUrl") String profileUrl) {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String newdate = dtf.format(now);

        try {

            System.out.println("Date:  "+newdate);
            User _user = userRepository
                    .save(new User(userName,name,email,password,phone,organizationEmail,newdate,newdate,
                            profileUrl,true));
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