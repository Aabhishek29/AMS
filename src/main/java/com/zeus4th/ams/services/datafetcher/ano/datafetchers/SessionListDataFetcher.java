package com.zeus4th.ams.services.datafetcher.ano.datafetchers;

import com.zeus4th.ams.model.ano.models.SessionList;
import com.zeus4th.ams.repository.ano.repository.SessionListRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class SessionListDataFetcher implements DataFetcher<List<SessionList>> {
    @Autowired
    private SessionListRepository sessionListRepository;

    @Override
    public List<SessionList> get(DataFetchingEnvironment environment) throws Exception {
        return sessionListRepository.findAll();
    }
}
