package com.zeus4th.ams.services;


import com.zeus4th.ams.model.UserDetails;

import java.util.List;

public interface UserDetailsServices {

    UserDetails createUserDetails(UserDetails userDetails);

    List<UserDetails> getAllUserDetails();

}
