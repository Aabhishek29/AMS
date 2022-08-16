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
public class AllUsersDetails implements DataFetcher<List<UserDetails>> {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Override
    public List<UserDetails> get(DataFetchingEnvironment environment) throws Exception {
        return userDetailsRepository.findAll();
    }
}
