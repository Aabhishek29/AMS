package com.zeus4th.ams.controlllers.gql;

import com.zeus4th.ams.model.User;
import com.zeus4th.ams.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Controller
@CrossOrigin(origins = {"http://localhost:8009"},
        allowedHeaders ={"Access-Control-Allow-Origin", "Access-Control-Allow-Headers","Authorization", "Cache-Control", "Content-Type"},
        allowCredentials = "true")
public class UserController {

    @Autowired
    private UserServices userServices;

    @MutationMapping("createUsers")
    public User createUser(
            @Argument String name,
            @Argument String userName,
            @Argument String email,
            @Argument String password,
            @Argument String phone,
            @Argument String organizationEmail,
            @Argument String profileUrl
    ){
        String userId = UUID.randomUUID().toString();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        ZoneId zoneid1 = ZoneId.of("Asia/Kolkata");
        LocalDateTime now = LocalDateTime.now(zoneid1);
        String createdAt = dtf.format(now);

        User user = new User();
        user.setName(name);
        user.setUserName(userName);
        user.setUserId(userId);
        user.setEmail(email);
        user.setPassword(password);
        user.setPhone(Long.parseLong(phone));
        user.setCreatedAt(createdAt);
        user.setUpdatedAt(createdAt);
        user.setOrganizationEmail(organizationEmail);
        user.setProfileUrl(profileUrl);
        user.setAuthenticated(true);
        return this.userServices.createUser(user);
    }

    @QueryMapping("allUsers")
    public List<User> getAllUsers(){
        return userServices.getAllUsers();
    }
}