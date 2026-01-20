package com.example.Projet_IRT31.service;

import com.example.Projet_IRT31.entity.Category;
import com.example.Projet_IRT31.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Créer une catégorie
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Récupérer toutes les catégories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Récupérer une catégorie par ID (Utile pour plus tard)
    public Optional<Category> getCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }
}
