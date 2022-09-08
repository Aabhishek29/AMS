package com.zeus4th.ams.services.implementations;

import com.zeus4th.ams.model.User;
import com.zeus4th.ams.repository.UserRepository;
import com.zeus4th.ams.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserServices {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findUserByUserId(userId);
    }
}
