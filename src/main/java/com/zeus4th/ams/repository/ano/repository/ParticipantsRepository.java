package com.zeus4th.ams.repository.ano.repository;

import com.zeus4th.ams.model.ano.models.Participants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantsRepository extends JpaRepository<Participants, String> {

        List<Participants> findBySessionId(String sessionId);
}
