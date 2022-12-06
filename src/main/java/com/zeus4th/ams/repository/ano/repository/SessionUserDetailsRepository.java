package com.zeus4th.ams.repository.ano.repository;

import com.zeus4th.ams.model.UserDetails;
import com.zeus4th.ams.model.ano.models.SessionDetails;
import com.zeus4th.ams.model.ano.models.SessionUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionUserDetailsRepository extends JpaRepository<SessionUserDetails,Number> {

    List<SessionUserDetails> findByUserDetails(UserDetails userDetails);

    List<SessionUserDetails> findBySessionDetails(SessionDetails sessionDetails);

}
