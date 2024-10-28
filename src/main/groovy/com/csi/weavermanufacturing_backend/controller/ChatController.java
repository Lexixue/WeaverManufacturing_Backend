package com.csi.weavermanufacturing_backend.controller;

import com.csi.weavermanufacturing_backend.dtos.SendMessageRequest;
import com.csi.weavermanufacturing_backend.entity.ChatHistory;
import com.csi.weavermanufacturing_backend.entity.Message;
import com.csi.weavermanufacturing_backend.entity.User;
import com.csi.weavermanufacturing_backend.enums.MessageType;
import com.csi.weavermanufacturing_backend.repository.ChatHistoryRepository;
import com.csi.weavermanufacturing_backend.repository.MessageRepository;
import com.csi.weavermanufacturing_backend.repository.UserRepository;
import com.csi.weavermanufacturing_backend.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ChatHistoryRepository chatHistoryRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/send", produces = "application/json", consumes = "application/json")
    public ResponseEntity<String> sendMessage(HttpServletRequest request, @RequestBody SendMessageRequest sendMessageRequest) {
        // Extract the token from the Authorization header
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Invalid or missing token");
        }

        String token = authHeader.substring(7);
        String username = jwtUtils.extractUsername(token);

        // Validate ChatHistory ID
        Optional<ChatHistory> chatHistoryOptional = chatHistoryRepository.findById(sendMessageRequest.getChatId());
        if (chatHistoryOptional.isEmpty()) {
            return ResponseEntity.status(404).body("ChatHistory with ID " + sendMessageRequest.getChatId() + " not found");
        }

        ChatHistory chatHistory = chatHistoryOptional.get();

        // Create a new message
        Message message = new Message();
        message.setChatHistory(chatHistory);
        message.setMessageType(sendMessageRequest.getMessageType());
        message.setTimestamp(LocalDateTime.now());
        message.setContent(sendMessageRequest.getContent());

        // Save the message
        messageRepository.save(message);

        return ResponseEntity.ok("Message successfully sent and recorded");
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<String> createChatHistory(HttpServletRequest request) {
        // Extract the token from the Authorization header
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Invalid or missing token");
        }

        String token = authHeader.substring(7);
        String username = jwtUtils.extractUsername(token);

        // Fetch the user based on the username extracted from the token
        User user = userRepository.findByUserName(username);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        // Create and save a new ChatHistory instance
        ChatHistory chatHistory = new ChatHistory();
        chatHistory.setUser(user);
        chatHistoryRepository.save(chatHistory);

        return ResponseEntity.ok("ChatHistory successfully created with ID: " + chatHistory.getChatId());
    }

    @GetMapping(value = "/{chatId}/messages", produces = "application/json")
    public ResponseEntity<?> getMessagesForChatHistory(HttpServletRequest request, @PathVariable("chatId") Integer chatId) {
        // Extract the token from the Authorization header
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Invalid or missing token");
        }

        String token = authHeader.substring(7);
        String username = jwtUtils.extractUsername(token);

        // Fetch the user based on the username extracted from the token
        User user = userRepository.findByUserName(username);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        // Validate ChatHistory ID
        Optional<ChatHistory> chatHistoryOptional = chatHistoryRepository.findById(chatId);
        if (chatHistoryOptional.isEmpty()) {
            return ResponseEntity.status(404).body("ChatHistory with ID " + chatId + " not found");
        }

        ChatHistory chatHistory = chatHistoryOptional.get();

        // Check if the ChatHistory belongs to the logged-in user
        if (!chatHistory.getUser().getUserId().equals(user.getUserId())) {
            return ResponseEntity.status(403).body("You do not have permission to access this ChatHistory");
        }

        // Get all messages for the ChatHistory
        List<Message> messages = chatHistory.getMessages();

        return ResponseEntity.ok(messages);
    }

}
