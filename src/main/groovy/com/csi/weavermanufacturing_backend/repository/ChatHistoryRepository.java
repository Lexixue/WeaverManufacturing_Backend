package com.csi.weavermanufacturing_backend.repository;

import com.csi.weavermanufacturing_backend.entity.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Integer> {
}
