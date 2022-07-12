package com.zeus4th.ams.services.datafetcher;

import com.zeus4th.ams.model.UserModelGql;
import com.zeus4th.ams.repository.UserRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDataFetcher implements DataFetcher<Optional<UserModelGql>> {

    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<UserModelGql> get(DataFetchingEnvironment environment) throws Exception {
        return userRepository.findOne(environment.getArgument("userId"));
    }
}
