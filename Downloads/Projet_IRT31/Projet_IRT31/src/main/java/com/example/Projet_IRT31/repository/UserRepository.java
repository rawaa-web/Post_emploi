package com.example.Projet_IRT31.repository;

import com.example.Projet_IRT31.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // On ajoute cette méthode pour trouver un utilisateur par son email (utile pour le login plus tard)
    Optional<User> findByEmail(String email);
    // Vérifier si un email existe déjà
    boolean existsByEmail(String email);
}