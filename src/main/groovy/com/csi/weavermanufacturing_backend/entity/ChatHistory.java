package com.csi.weavermanufacturing_backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class ChatHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "chatHistory", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Message> messages;
}
