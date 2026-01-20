package com.example.Projet_IRT31.repository;

import com.example.Projet_IRT31.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// 2. JobRepository.java
@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    // Trouver les offres qui contiennent le mot-clé dans le titre (ex: "Développeur Java")

    // Trouver les offres d'un employeur spécifique
    List<Job> findByEmployerId(Integer employerId);

    // Trouver par catégorie et statut (ex: toutes les offres IT validées)
    List<Job> findByCategoryIdAndStatus(Integer categoryId, String status);


        // Rechercher par titre (ex: "Développeur")
        List<Job> findByTitleContainingIgnoreCase(String keyword);

        // Rechercher par ville (ex: "Paris")
        List<Job> findByLocationIgnoreCase(String location);

        // Rechercher par les deux
        List<Job> findByTitleContainingIgnoreCaseAndLocationIgnoreCase(String title, String location);


    // Requête complexe : Trouver les offres qui demandent une compétence précise
    // On utilise JPQL ici
    @Query("SELECT j FROM Job j JOIN j.skills s WHERE s.name = :skillName")
    List<Job> findJobsBySkill(@Param("skillName") String skillName);
}
