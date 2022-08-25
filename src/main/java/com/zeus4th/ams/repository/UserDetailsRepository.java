package com.zeus4th.ams.repository;

import com.zeus4th.ams.model.User;
import com.zeus4th.ams.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.ls.LSInput;

import java.util.List;

public interface UserDetailsRepository extends JpaRepository<UserDetails,String> {

    List<UserDetails> findUserDetailsBySessionListId(String userId);

    List<UserDetails> findByUser(User user);

    List<UserDetails> findByAppId(String appId);

    List<UserDetails> findByAppIdAndDeactivateUserAndSessionListId(String appId, Boolean deactivateUser, String sessionListId);

    List<UserDetails> findByDeactivateUser(Boolean deactivateUser);

    List<UserDetails> findBySessionListId(String sessionListId);

    List<UserDetails> findByAppIdAndSessionListId(String appId, String sessionListId);

    List<UserDetails> findBySessionListIdAndDeactivateUser(String sessionListId, Boolean deactivateUser);

    List<UserDetails> findByAppIdAndDeactivateUser(String appId, Boolean deactivateUser);
}
