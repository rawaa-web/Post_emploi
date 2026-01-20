package com.example.Projet_IRT31.service;

import com.example.Projet_IRT31.entity.User;
import com.example.Projet_IRT31.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Inscription
    public User register(User user) {
        return userRepository.save(user);
    }

    // Connexion (Simple vérification)
    public User login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("Email ou mot de passe incorrect"));
    }

    // Récupérer tout le monde (pour l'admin)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Récupérer par ID
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
}