package com.zeus4th.ams.repository.ano.repository;

import com.zeus4th.ams.model.ano.models.SessionList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionListRepository extends JpaRepository<SessionList, Long> {

    List<SessionList> findById(long id);
}
