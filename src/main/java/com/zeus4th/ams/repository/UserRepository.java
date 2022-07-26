package com.zeus4th.ams.repository;

import java.util.List;

import com.zeus4th.ams.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByAuthenticated(boolean authenticated);
    List<User> findByIdContaining(String id);
    List<User> findByUserNameEquals(String userName);
}