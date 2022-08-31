package com.zeus4th.ams.services.datafetcher;

import java.util.ArrayList;
import java.util.List;

import com.zeus4th.ams.model.User;
import com.zeus4th.ams.model.UserDetails;
import com.zeus4th.ams.repository.UserDetailsRepository;
import com.zeus4th.ams.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;


@Slf4j
@Component
public class AllUserDataFetcher implements DataFetcher<List<User>>{

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserDetailsRepository userDetailsRepository;

  @Override
  public List<User> get(DataFetchingEnvironment environment) throws Exception {
    log.info("Data Fetcher Work AllUserDataFetcher");
    String userId = null;
    try {
       userId = environment.getArgument("userId");
       if(userId==null){
         userId = environment.getArgument("phone");
         if(userId!=null){
           return userRepository.findByPhone(Long.parseLong(userId));
         }
       }
    }catch (Exception e){
      log.info("No UserId send with query");
    }
    if(userId==null){
      log.info("UserId is null ");
      return userRepository.findAll();
    }
    List<User> finalAnswer = new ArrayList<>();
    User user = userRepository.findUserByUserId(userId).get(0);
    List<UserDetails> ud  = new ArrayList<>();
    try {
      ud = userDetailsRepository.findByUser(user);
      log.error(String.valueOf(ud.get(0).getSessionListId()));
    }
    catch (Exception e){
      ud = null;
      log.error(e.toString());
    }
    user.setUserDetails(ud);
    finalAnswer.add(user);
    return finalAnswer ;
  }

}
