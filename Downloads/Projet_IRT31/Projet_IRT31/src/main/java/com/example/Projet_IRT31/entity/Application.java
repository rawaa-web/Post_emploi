package com.example.Projet_IRT31.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "candidatures")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Lien vers l'offre concernée
    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    // Lien vers le candidat (User)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String status; // ex: "EN_ATTENTE", "ACCEPTE", "REFUSE"
    private LocalDateTime applicationDate;

    // Constructeur par défaut (Important pour JPA)
    public Application() {
        this.applicationDate = LocalDateTime.now(); // Date automatique
        this.status = "EN_ATTENTE"; // Statut par défaut
    }

    // ==========================
    //    GETTERS ET SETTERS
    // ==========================

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getApplicationDate() { return applicationDate; }
    public void setApplicationDate(LocalDateTime applicationDate) { this.applicationDate = applicationDate; }
}