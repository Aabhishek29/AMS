package com.zeus4th.ams.implementations.ano;

import com.zeus4th.ams.model.ano.models.SessionDetails;
import com.zeus4th.ams.repository.ano.repository.SessionDetailsRepository;
import com.zeus4th.ams.services.ano.SessionDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionDetailsServicesImple implements SessionDetailsServices {

    private SessionDetailsRepository sessionDetailsRepository;

    @Autowired
    public void SessionDetailsRepository(SessionDetailsRepository sessionDetailsRepository) {
        this.sessionDetailsRepository = sessionDetailsRepository;
    }

    @Override
    public List<SessionDetails> getAllSessions(String sessionId) {
        return sessionDetailsRepository.findBySessionId(sessionId);
    }

    @Override
    public SessionDetails createSession(SessionDetails sessionDetails) {
        return sessionDetailsRepository.save(sessionDetails);
    }
}
