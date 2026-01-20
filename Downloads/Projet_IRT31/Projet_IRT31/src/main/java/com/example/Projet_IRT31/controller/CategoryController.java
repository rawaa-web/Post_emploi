package com.example.Projet_IRT31.controller;

import com.example.Projet_IRT31.entity.Category;
import com.example.Projet_IRT31.service.CategoryService; // <-- On importe le Service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService; // <-- On injecte le Service (plus le Repository)

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        // On passe par le service
        Category newCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(newCategory);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        // On passe par le service
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}