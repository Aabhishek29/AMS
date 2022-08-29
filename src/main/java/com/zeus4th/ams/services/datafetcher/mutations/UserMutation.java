//package com.zeus4th.ams.services.datafetcher.mutations;
//
//import com.coxautodev.graphql.tools.GraphQLMutationResolver;
//import com.zeus4th.ams.model.User;
//import com.zeus4th.ams.repository.UserRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.util.UUID;
//
//@Slf4j
//@Component
//public class UserMutation implements GraphQLMutationResolver {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public User createUsers(String userName, String name, String email, String password, String phone, String organizationEmail, String profileUrl){
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//        ZoneId zoneid1 = ZoneId.of("Asia/Kolkata");
//        LocalDateTime now = LocalDateTime.now(zoneid1);
//        String newdate = dtf.format(now);
//        String userId = UUID.randomUUID().toString();
//        try {
//            return userRepository
//                    .save(new User(userId, userName, name, email, password, Long.parseLong(phone), organizationEmail, newdate, newdate,
//                            profileUrl, true, false));
//        }catch (Exception e) {
//            log.error("There is Some error in UserMutation", e);
//            return null;
//        }
//    }
//}
