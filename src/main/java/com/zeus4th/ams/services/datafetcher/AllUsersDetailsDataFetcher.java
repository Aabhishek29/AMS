package com.zeus4th.ams.services.datafetcher;


import com.zeus4th.ams.model.UserDetails;
import com.zeus4th.ams.repository.UserDetailsRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class AllUsersDetailsDataFetcher implements DataFetcher<List<UserDetails>> {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public List<UserDetails> get(DataFetchingEnvironment environment) throws Exception {
        String appId = null,sessionListId = null, deactivateUser = null;
        appId = environment.getArgument("appId");
        sessionListId = environment.getArgument("sessionListId");
        deactivateUser = environment.getArgument("deactivateUser");
        if(sessionListId==null && deactivateUser==null && appId==null){
            return userDetailsRepository.findAll();
        }
        else if(sessionListId==null && deactivateUser==null){
            return userDetailsRepository.findByAppId(appId);
        }
        else if(sessionListId==null && appId == null){
            return userDetailsRepository.findByDeactivateUser(Boolean.valueOf(deactivateUser));
        }else if(appId == null && deactivateUser==null){
            return userDetailsRepository.findBySessionListId(sessionListId);
        }else if(appId == null ){
            return userDetailsRepository.findBySessionListIdAndDeactivateUser(sessionListId,Boolean.valueOf(deactivateUser));
        }else if(sessionListId == null){
            return userDetailsRepository.findByAppIdAndDeactivateUser(appId,Boolean.valueOf(deactivateUser));
        }else if(deactivateUser==null){
            return  userDetailsRepository.findByAppIdAndSessionListId(appId,sessionListId);
        }else {
            return userDetailsRepository.findByAppIdAndDeactivateUserAndSessionListId(appId, Boolean.valueOf(deactivateUser), sessionListId);
        }
    }
}
