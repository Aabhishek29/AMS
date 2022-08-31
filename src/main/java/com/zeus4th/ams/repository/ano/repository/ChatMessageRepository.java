package com.zeus4th.ams.repository.ano.repository;

import com.zeus4th.ams.model.ano.models.ChatMessages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessages, String> {
}
