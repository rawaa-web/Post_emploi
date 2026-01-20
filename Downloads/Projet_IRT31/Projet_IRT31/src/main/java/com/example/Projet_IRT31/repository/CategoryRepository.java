package com.example.Projet_IRT31.repository;

import com.example.Projet_IRT31.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // Idem, findById est inclus automatiquement.
}
