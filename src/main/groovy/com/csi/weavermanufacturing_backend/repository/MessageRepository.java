package com.csi.weavermanufacturing_backend.repository;

import com.csi.weavermanufacturing_backend.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}

