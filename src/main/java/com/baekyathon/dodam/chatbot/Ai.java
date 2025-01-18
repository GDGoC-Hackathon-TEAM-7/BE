package com.baekyathon.dodam.chatbot;

import com.baekyathon.dodam.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Ai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String message;
    private String botResponse;
    private LocalDateTime createTime;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    protected void onCreate() {
        this.createTime = LocalDateTime.now();
    }

    @Builder
    public Ai(String userMessage, String botResponse, User user) {
        this.message = message;
        this.botResponse = botResponse;
        this.user = user;
        this.createTime = LocalDateTime.now();
    }


}

