package com.zeus4th.ams.controlllers.gql.ano;


import com.zeus4th.ams.model.ano.models.AnoyUser;
import com.zeus4th.ams.services.AnoyUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class AnoyUserController {

    @Autowired
    private AnoyUserServices anoyUserServices;

    @QueryMapping("allAnoyUser")
    public List<AnoyUser> getAllAnoyUser(
            @Argument String userId
    ) {
        List<AnoyUser> anoyUserList = new ArrayList<>();
        if(userId!=null){
            anoyUserList.add(anoyUserServices.getByuserId(userId));
            return anoyUserList;
        }
        return anoyUserServices.getAllAnoys();
    }

    @MutationMapping("createAnoyUser")
    public AnoyUser createAnoyUser(
            @Argument String userName,
            @Argument String profileUrl,
            @Argument String clientToken
    ){
        String userId = UUID.randomUUID().toString();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        ZoneId zoneid1 = ZoneId.of("Asia/Kolkata");
        LocalDateTime now = LocalDateTime.now(zoneid1);
        String createdAt = dtf.format(now);

        AnoyUser anoyUser = new AnoyUser();
        anoyUser.setUserName(userName);
        anoyUser.setProfileUrl(profileUrl);
        anoyUser.setCreatedAt(createdAt);
        anoyUser.setUpdatedAt(createdAt);
        anoyUser.setUserId(userId);
        anoyUser.setClientToken(clientToken);
        anoyUser.setAuthenticated(true);
        return anoyUserServices.createAnoyUser(anoyUser);
    }


}
