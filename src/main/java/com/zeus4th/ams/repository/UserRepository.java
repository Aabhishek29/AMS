package com.zeus4th.ams.repository;

import java.util.List;

import com.zeus4th.ams.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByPublished(boolean is_authenticated);
    List<User> findByTitleContaining(String user_id);
}