package com.zeus4th.ams.repository.ano.repository;

import com.zeus4th.ams.model.ano.models.AnoyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnoyUserRepository extends JpaRepository<AnoyUser,String> {


    AnoyUser findByUserId(String userId);

}
