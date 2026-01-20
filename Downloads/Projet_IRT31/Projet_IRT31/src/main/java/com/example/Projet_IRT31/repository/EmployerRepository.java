package com.example.Projet_IRT31.repository;

import com.example.Projet_IRT31.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Integer> {
    // JpaRepository nous donne déjà findById, save, delete, etc.
    // Pas besoin d'écrire de code ici pour l'instant.
}
