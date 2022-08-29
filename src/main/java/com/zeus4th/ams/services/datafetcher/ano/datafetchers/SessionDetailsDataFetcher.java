package com.zeus4th.ams.services.datafetcher.ano.datafetchers;

import com.zeus4th.ams.model.ano.models.SessionDetails;
import com.zeus4th.ams.repository.ano.repository.SessionDetailsRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class SessionDetailsDataFetcher implements DataFetcher<List<SessionDetails>> {

    @Autowired
    private SessionDetailsRepository sessionDetailsRepository;

    @Override
    public List<SessionDetails> get(DataFetchingEnvironment environment) throws Exception {
        log.error("Hello world");
        System.out.println("We CAll This function");
        log.error(sessionDetailsRepository.findAll().toString());
        return sessionDetailsRepository.findAll();
    }
}
