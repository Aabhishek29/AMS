package com.zeus4th.ams.services;

import com.zeus4th.ams.model.User;

import java.util.List;

public interface UserServices {

    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(String userId);

    List<User> getAllAuthenticatedUsers();

    List<User> getUsersByOrganizationEmail(String organizationEmail);

    List<User> getUserByPhone(Long phone);

}
