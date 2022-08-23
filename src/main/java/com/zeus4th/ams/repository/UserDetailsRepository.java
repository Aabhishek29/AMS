package com.zeus4th.ams.repository;

import com.zeus4th.ams.model.User;
import com.zeus4th.ams.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserDetailsRepository extends JpaRepository<UserDetails,String> {

    List<UserDetails> findUserDetailsBySessionListId(String userId);

    List<UserDetails> findByUser(User user);
}
