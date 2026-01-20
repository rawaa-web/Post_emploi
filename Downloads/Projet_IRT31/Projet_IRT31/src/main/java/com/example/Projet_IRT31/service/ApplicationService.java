package com.example.Projet_IRT31.service;

import com.example.Projet_IRT31.entity.Application;
import com.example.Projet_IRT31.entity.Job;
import com.example.Projet_IRT31.entity.User;
import com.example.Projet_IRT31.repository.ApplicationRepository;
import com.example.Projet_IRT31.repository.JobRepository;
import com.example.Projet_IRT31.repository.UserRepository; // Attention, il faudra peut-être créer ce fichier si vous ne l'avez pas
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private UserRepository userRepository;
    public long countApplications(Integer jobId) {
        return applicationRepository.countByJobId(jobId);
    }
    public Application apply(Integer jobId, Integer userId) {
        // 1. Récupérer l'offre
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Offre introuvable"));

        // 2. Récupérer le candidat
        User candidate = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Candidat introuvable"));

        // 3. Créer la candidature
        Application application = new Application();
        application.setJob(job);
        application.setUser(candidate);

        return applicationRepository.save(application);
    }

    public List<Application> getApplicationsByJob(Integer jobId) {
        return applicationRepository.findByJobId(jobId);
    }
    // Mettre à jour le statut (ex: ACCEPTEE, REFUSEE, EN_ATTENTE)
    public Application updateStatus(Integer applicationId, String newStatus) {
        // 1. Chercher la candidature
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Candidature introuvable"));

        // 2. Modifier le statut
        application.setStatus(newStatus);

        // 3. Sauvegarder
        return applicationRepository.save(application);
    }

}