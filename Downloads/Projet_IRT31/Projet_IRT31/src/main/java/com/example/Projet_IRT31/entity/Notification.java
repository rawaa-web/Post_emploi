package com.example.Projet_IRT31.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data @NoArgsConstructor
public class Notification {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String content;
    private boolean isRead = false;
    private LocalDateTime createdAt = LocalDateTime.now();
}
