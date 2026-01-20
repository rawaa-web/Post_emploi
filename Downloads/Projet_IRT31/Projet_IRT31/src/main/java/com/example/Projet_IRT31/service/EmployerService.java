package com.example.Projet_IRT31.service;

import com.example.Projet_IRT31.entity.Employer;
import com.example.Projet_IRT31.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployerService {

    @Autowired
    private EmployerRepository employerRepository;

    // 1. Créer un nouvel employeur
    public Employer createEmployer(Employer employer) {
        return employerRepository.save(employer);
    }

    // 2. Récupérer la liste de tous les employeurs
    public List<Employer> getAllEmployers() {
        return employerRepository.findAll();
    }

    // 3. Récupérer un seul employeur par son ID (pour vérifier les détails)
    public Employer getEmployerById(Integer id) {
        return employerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employeur introuvable avec l'ID : " + id));
    }

    // 4. Supprimer un employeur (Attention : cela peut supprimer ses offres en cascade selon config)
    public void deleteEmployer(Integer id) {
        if (!employerRepository.existsById(id)) {
            throw new RuntimeException("Employeur introuvable, suppression impossible.");
        }
        employerRepository.deleteById(id);
    }
}