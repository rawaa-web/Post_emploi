package com.example.Projet_IRT31.entity;

import jakarta.persistence.*; // ou javax.persistence.*

@Entity
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Le lien vers l'utilisateur
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String companyName;
    private String website;
    private String description;

    // ==========================================
    //    VOICI LES GETTERS ET SETTERS MANQUANTS
    // ==========================================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // C'est cette méthode qui vous manquait !
    public User getUser() {
        return user;
    }

    // C'est celle-ci qui provoquait l'erreur "non reconnu"
    public void setUser(User user) {
        this.user = user;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}