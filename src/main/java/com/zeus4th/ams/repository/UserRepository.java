package com.zeus4th.ams.repository;

import java.util.List;
import java.util.Optional;

import com.zeus4th.ams.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByAuthenticated(boolean authenticated);
    Optional<User> findByUserId(String userId);
    List<User> findByUserNameEquals(String userName);
    List<User> findByEmail(String email);
    List<User> findByOrganizationEmail(String organizationEmail);

   User findUserByUserId(String userId);

    List<User> findByPhone(long phone);
}