package com.zeus4th.ams.repository;

import com.zeus4th.ams.model.UserModelGql;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModelGql,String> {
}
