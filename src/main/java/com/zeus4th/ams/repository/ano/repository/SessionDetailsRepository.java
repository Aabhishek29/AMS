package com.zeus4th.ams.repository.ano.repository;

import com.zeus4th.ams.model.ano.models.SessionDetails;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionDetailsRepository extends JpaRepository<SessionDetails, String> {


    List<SessionDetails> findBySessionId(String sessionId);


}
