package com.zeus4th.ams.services.datafetcher.ano.datafetchers;

import com.zeus4th.ams.model.ano.models.ChatMessages;
import com.zeus4th.ams.repository.ano.repository.ChatMessageRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChatMessageDataFetcher implements DataFetcher<List<ChatMessages>> {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Override
    public List<ChatMessages> get(DataFetchingEnvironment environment) throws Exception {
        return chatMessageRepository.findAll();
    }
}
