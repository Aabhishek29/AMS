package com.zeus4th.ams.ano.utilitise;

import com.zeus4th.ams.model.ano.models.SessionDetails;
import com.zeus4th.ams.repository.ano.repository.SessionDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

//@Service
public class SessionUtils {

//    @Autowired
//    private SessionDetailsRepository sessionDetailsRepository;

    public SessionUtils() { }

    public void createSession(
            String creator,
            List<String> recivers
    ) {
        SessionDetails sessionDetails = new SessionDetails();

    }

}
