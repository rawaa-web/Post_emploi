package com.example.Projet_IRT31.repository;

import com.example.Projet_IRT31.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    // CORRECTION ICI : On utilise UserId, pas CandidateId
    boolean existsByJobIdAndUserId(Integer jobId, Integer userId);

    List<Application> findByJobId(Integer jobId);
    long countByJobId(Integer jobId);
}