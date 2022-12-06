package com.zeus4th.ams.controlllers.gql.ano;

import com.zeus4th.ams.model.UserDetails;
import com.zeus4th.ams.model.ano.models.SessionDetails;
import com.zeus4th.ams.model.ano.models.SessionUserDetails;
import com.zeus4th.ams.repository.UserDetailsRepository;
import com.zeus4th.ams.repository.ano.repository.SessionUserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class SessionUserDetailsController {
    @Autowired
    private SessionUserDetailsRepository sessionUserDetailsRepository;
    @Autowired
    private UserDetailsRepository userDetailsRepository;
    public List<SessionUserDetails> allSessionUserDetails(){
        return sessionUserDetailsRepository.findAll();
    }



}
