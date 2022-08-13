package com.zeus4th.ams.services.datafetcher;

import java.util.List;
import java.util.Optional;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.zeus4th.ams.model.User;
import com.zeus4th.ams.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;


@Slf4j
@Component
public class UserDataFetcher implements DataFetcher<List<User>>{

  @Autowired
  private UserRepository userRepository;

  @Override
  public List<User> get(DataFetchingEnvironment environment) {
    return userRepository.findAll();
  }

}
