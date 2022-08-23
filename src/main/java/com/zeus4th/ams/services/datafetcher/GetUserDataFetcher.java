package com.zeus4th.ams.services.datafetcher;


import com.zeus4th.ams.model.User;
import com.zeus4th.ams.model.UserDetails;
import com.zeus4th.ams.repository.UserDetailsRepository;
import com.zeus4th.ams.repository.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class GetUserDataFetcher implements DataFetcher<List<User>> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public List<User> get(DataFetchingEnvironment environment) throws Exception {
        log.info("Data Fetcher Work getUserDetails");

        //TODO
        // need to add a check whether the userId and username belongs to same person or not

        String userId = environment.getArgument("userId");
        if(userId==null){
            log.info("UserId is null ");
        }
        List<User> finalAnswer = new ArrayList<>();
//        String userName = environment.getArgument("userName");
        User user = userRepository.findUserByUserId(userId).get(0);
        List<UserDetails> ud = userDetailsRepository.findByUser(user);
        log.error(String.valueOf(ud.get(0).getSessionListId()));

        user.setUserDetails(ud);
        log.error(String.valueOf(user.getUserDetails().size()));
        log.error(String.valueOf(user.getUserDetails().get(0).getSessionListId()));
        log.error(String.valueOf(finalAnswer.get(0).getUserDetails().get(0).getSessionListId()));
        finalAnswer.add(user);
        return finalAnswer ;
    }
}
