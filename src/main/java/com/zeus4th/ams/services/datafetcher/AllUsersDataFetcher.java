package com.zeus4th.ams.services.datafetcher;

import com.zeus4th.ams.model.UserModelGql;
import com.zeus4th.ams.repository.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllUsersDataFetcher implements DataFetcher<List<UserModelGql>> {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserModelGql> get(DataFetchingEnvironment environment) throws Exception {
        return userRepository.findAll();
    }
}
