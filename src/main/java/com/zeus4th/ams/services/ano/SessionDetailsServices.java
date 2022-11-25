package com.zeus4th.ams.services.ano;

import com.zeus4th.ams.model.ano.models.SessionDetails;

import java.util.List;

public interface SessionDetailsServices {

    List<SessionDetails> getAllSessions(String sessionId);

    SessionDetails createSession(SessionDetails sessionDetails);

}
