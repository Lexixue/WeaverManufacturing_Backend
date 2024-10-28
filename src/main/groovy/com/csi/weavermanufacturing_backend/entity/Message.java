package com.csi.weavermanufacturing_backend.entity;

import com.csi.weavermanufacturing_backend.enums.MessageType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageId;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private ChatHistory chatHistory;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;  // USER or AI

    private LocalDateTime timestamp;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
}

