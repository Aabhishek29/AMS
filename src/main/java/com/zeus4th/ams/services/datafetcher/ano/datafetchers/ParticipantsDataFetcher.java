package com.zeus4th.ams.services.datafetcher.ano.datafetchers;

import com.zeus4th.ams.model.ano.models.Participants;
import com.zeus4th.ams.repository.ano.repository.ParticipantsRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParticipantsDataFetcher implements DataFetcher<List<Participants>> {

    @Autowired
    private ParticipantsRepository participantsRepository;

    @Override
    public List<Participants> get(DataFetchingEnvironment environment) throws Exception {
        return participantsRepository.findAll();
    }
}
