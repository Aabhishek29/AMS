package com.zeus4th.ams.services.datafetcher;


import com.zeus4th.ams.model.User;
import com.zeus4th.ams.repository.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Slf4j
@Component
public class GetUserDataFetcher implements DataFetcher<List<User>> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> get(DataFetchingEnvironment environment) throws Exception {
        log.info("Data Fetcher Work getUserDetails");

        //TODO
        // need to add a check whether the userId and username belongs to same person or not

        String userId = environment.getArgument("userId");
        if(userId==null){
            log.info("UserId is null ");
        }
        String userName = environment.getArgument("userName");
        return userId==null? userRepository.findByUserNameEquals(userName) : userRepository.findUserByUserId(userId);
    }
}
