package com.zeus4th.ams.controlllers.gql.ano;

import com.zeus4th.ams.model.ano.models.SessionDetails;
import com.zeus4th.ams.services.ano.SessionDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SessionDetailController {

    @Autowired
    private SessionDetailsServices sessionDetailsServices;

    @QueryMapping("sessionDetails")
    public List<SessionDetails> getAllSessions(
            @Argument String sessionId
    ){
        return sessionDetailsServices.getAllSessions(sessionId);
    }


}
