package com.zeus4th.ams.implementations;

import com.zeus4th.ams.model.UserDetails;
import com.zeus4th.ams.repository.UserDetailsRepository;
import com.zeus4th.ams.repository.UserRepository;
import com.zeus4th.ams.services.UserDetailsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsImpl implements UserDetailsServices {

    private UserDetailsRepository userDetailsRepository;

    @Autowired
    public UserDetailsImpl(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public UserDetails createUserDetails(UserDetails userDetails) {
        return this.userDetailsRepository.save(userDetails);
    }

    @Override
    public List<UserDetails> getAllUserDetails() {
        return this.userDetailsRepository.findAll();
    }
}
