package com.example.Projet_IRT31.controller;

import com.example.Projet_IRT31.entity.Job;
import com.example.Projet_IRT31.repository.ApplicationRepository;
import com.example.Projet_IRT31.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired // Utilisons l'autowired simple pour plus de clarté
    private JobService jobService;

    @Autowired
    private ApplicationRepository applicationRepository;
    @GetMapping("/test")
    public String test() {
        return "CA MARCHE !";
    }
    // --- CRÉATION ---
    @PostMapping
    public ResponseEntity<Job> createJob(
            @RequestBody Job job,
            @RequestParam(required = false) Integer employerId,
            @RequestParam(required = false) Integer categoryId) {

        Job newJob = jobService.createJob(job, employerId, categoryId);
        return new ResponseEntity<>(newJob, HttpStatus.CREATED);
    }

    // --- RECHERCHE ---
    @GetMapping("/search")
    public ResponseEntity<List<Job>> searchJobs(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String location) {
        List<Job> jobs = jobService.search(keyword, location);
        return ResponseEntity.ok(jobs);
    }

    // --- LECTURE (TOUS) ---
    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllValidJobs());
    }

    // --- LECTURE (ID) ---
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Integer id) {
        return jobService.getById(id)
                .map(job -> ResponseEntity.ok().body(job))
                .orElse(ResponseEntity.notFound().build());
    }

    // --- MODIFICATION ---
    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Integer id, @RequestBody Job jobDetails) {
        Job updatedJob = jobService.updateJob(id, jobDetails);
        return ResponseEntity.ok(updatedJob);
    }

    // --- SUPPRESSION ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Integer id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }

    // --- STATISTIQUES ---
    @GetMapping("/{id}/stats")
    public ResponseEntity<String> getJobStats(@PathVariable Integer id) {
        long count = applicationRepository.countByJobId(id);
        return ResponseEntity.ok("L'offre #" + id + " a reçu " + count + " candidatures.");
    }
}