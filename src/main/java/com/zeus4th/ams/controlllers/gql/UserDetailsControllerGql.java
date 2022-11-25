package com.zeus4th.ams.controlllers.gql;

import com.zeus4th.ams.model.User;
import com.zeus4th.ams.model.UserDetails;
import com.zeus4th.ams.repository.UserRepository;
import com.zeus4th.ams.services.UserDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Controller
public class UserDetailsControllerGql {

    @Autowired
    private UserDetailsServices userDetailsServices;

    @Autowired
    private UserRepository userRepository;

    @MutationMapping("addUserDetails")
    public UserDetails addUserDetails(
            @Argument String userId,
            @Argument String appId,
            @Argument String profileUrl
    ) {
        String sessionListId = UUID.randomUUID().toString();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        ZoneId zoneid1 = ZoneId.of("Asia/Kolkata");
        LocalDateTime now = LocalDateTime.now(zoneid1);
        String createdAt = dtf.format(now);
        User user = userRepository.findUserByUserId(userId);
        if(user==null){
            return null;
        }

        UserDetails userDetails = new UserDetails();

        userDetails.setSessionListId(sessionListId);
        userDetails.setCreatedAt(createdAt);
        userDetails.setUpdatedAt(createdAt);
        userDetails.setUser(user);
        userDetails.setAppId(appId);
        userDetails.setStatus("valid");
        userDetails.setPofileUrl(profileUrl);
        userDetails.setDeactivateUser(false);

        return this.userDetailsServices.createUserDetails(userDetails);
    }

    @QueryMapping("allUserDetails")
    public List<UserDetails> allUserDetails(
            @Argument String userId
    ) {
        return userDetailsServices.getAllUserDetails();
    }
}
