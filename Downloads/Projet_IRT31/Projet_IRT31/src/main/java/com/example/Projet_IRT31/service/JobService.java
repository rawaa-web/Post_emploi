package com.example.Projet_IRT31.service;

import com.example.Projet_IRT31.entity.Category;
import com.example.Projet_IRT31.entity.Employer;
import com.example.Projet_IRT31.entity.Job;
import com.example.Projet_IRT31.repository.CategoryRepository;
import com.example.Projet_IRT31.repository.EmployerRepository;
import com.example.Projet_IRT31.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final EmployerRepository employerRepository;
    private final CategoryRepository categoryRepository;

    // --- CONSTRUCTEUR MANUEL (Solution à "might not have been initialized") ---
    public JobService(JobRepository jobRepository,
                      EmployerRepository employerRepository,
                      CategoryRepository categoryRepository) {
        this.jobRepository = jobRepository;
        this.employerRepository = employerRepository;
        this.categoryRepository = categoryRepository;
    }

    // Méthode pour publier une offre
    public Job createJob(Job job, Integer employerId, Integer categoryId) {

        // 1. GESTION EMPLOYEUR (Seulement si un ID est fourni)
        if (employerId != null) {
            Employer employer = employerRepository.findById(employerId)
                    .orElseThrow(() -> new RuntimeException("Employeur introuvable avec l'ID : " + employerId));
            job.setEmployer(employer);
        }

        // 2. GESTION CATÉGORIE (Seulement si un ID est fourni)
        // C'est ce bloc 'if' qui empêche votre erreur "given id must not be null"
        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Catégorie introuvable avec l'ID : " + categoryId));
            job.setCategory(category);
        }

        // 3. NETTOYAGE (Important !)
        // On ne génère JAMAIS l'ID manuellement avec Random(), on laisse la base de données gérer.
        // job.setId(...) <--- SUPPRIMÉ

        // 4. STATUT PAR DÉFAUT
        // Si l'utilisateur n'a pas envoyé de statut dans le JSON, on met "OUVERT" ou "EN_ATTENTE" par défaut
        if (job.getStatus() == null || job.getStatus().isEmpty()) {
            job.setStatus("EN_ATTENTE");
        }

        // 5. SAUVEGARDE
        return jobRepository.save(job);
    }
    // Méthode de recherche intelligente
    // Dans JobService.java

    public List<Job> search(String keyword, String location) {
        if (keyword != null && location != null) {
            return jobRepository.findByTitleContainingIgnoreCaseAndLocationIgnoreCase(keyword, location);
        } else if (keyword != null) {
            return jobRepository.findByTitleContainingIgnoreCase(keyword);
        } else if (location != null) {
            return jobRepository.findByLocationIgnoreCase(location);
        } else {
            return jobRepository.findAll();
        }
    }


    // Récupérer les offres validées
    public List<Job> getAllValidJobs() {
        return jobRepository.findAll().stream()
                .filter(job -> "valide".equals(job.getStatus()))
                .collect(Collectors.toList());
    }
    // 1. MODIFIER une offre existante
    public Job updateJob(Integer id, Job jobDetails) {
        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job non trouvé"));

        // Mise à jour des champs simples
        existingJob.setTitle(jobDetails.getTitle());
        existingJob.setDescription(jobDetails.getDescription());
        existingJob.setLocation(jobDetails.getLocation());
        existingJob.setSalaryRange(jobDetails.getSalaryRange());
        existingJob.setTypeContrat(jobDetails.getTypeContrat());

        // 👇 LE PLUS IMPORTANT POUR VOUS MAINTENANT 👇
        if (jobDetails.getStatus() != null) {
            existingJob.setStatus(jobDetails.getStatus());
        }

        return jobRepository.save(existingJob);
    }

    // 2. SUPPRIMER une offre
    public void deleteJob(Integer id) {
        if (!jobRepository.existsById(id)) {
            throw new RuntimeException("Impossible de supprimer : Offre introuvable");
        }
        jobRepository.deleteById(id);
    }

    // (Bonus) Récupérer une seule offre par son ID (utile pour le détail)
    public Job getJobById(Integer id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offre introuvable"));
    }
    public Optional<Job> getById(Integer id) {
        return jobRepository.findById(id);
    }
}