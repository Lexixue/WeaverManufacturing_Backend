package com.csi.weavermanufacturing_backend.dtos;

import com.csi.weavermanufacturing_backend.enums.MessageType;
import lombok.Data;

@Data
public class SendMessageRequest {
    private Integer chatId; // ID of the ChatHistory
    private MessageType messageType; // Type of the message (USER or AI)
    private String content; // Message content
}

