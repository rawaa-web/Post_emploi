package com.example.Projet_IRT31.entity; // Vérifiez votre package

import jakarta.persistence.*; // Si ça ne marche pas, essayez javax.persistence.*
import java.util.List;

@Entity
@Table(name = "users") // ⚠️ TRES IMPORTANT : "user" est un mot interdit en SQL, il faut utiliser "users"
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String email;
    private String password;

    // (Optionnel) Si vous voulez définir un rôle : ADMIN, RECRUITER, CANDIDATE
    private String role;

    // ==========================
    //    GETTERS ET SETTERS
    // ==========================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    @Column(columnDefinition = "TEXT") // Permet de stocker un long texte
    private String resumeText; // Ex: "Développeur Java avec 3 ans d'expérience..."

    private String skills; // Ex: "Java, Spring, SQL"
}