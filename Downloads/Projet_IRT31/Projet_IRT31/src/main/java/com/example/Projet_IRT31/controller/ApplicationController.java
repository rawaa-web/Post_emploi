package com.example.Projet_IRT31.controller;


import com.example.Projet_IRT31.entity.Application;
import com.example.Projet_IRT31.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin("*")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // POST /api/applications?jobId=5&userId=2
    @PostMapping
    public ResponseEntity<Application> applyToJob(@RequestParam Integer jobId,
                                                  @RequestParam Integer userId) {
        Application newApplication = applicationService.apply(jobId, userId);
        return ResponseEntity.ok(newApplication);
    }

    // GET /api/applications/job/5 -> Voir qui a postulé à l'offre 5
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<Application>> getApplicationsForJob(@PathVariable Integer jobId) {
        return ResponseEntity.ok(applicationService.getApplicationsByJob(jobId));
    }

    // PUT /api/applications/5/status?status=ACCEPTEE
    // PUT /api/applications/5/status?newStatus=ACCEPTEE
    @PutMapping("/{id}/status")
    public ResponseEntity<Application> updateApplicationStatus(
            @PathVariable Integer id,
            @RequestParam String newStatus) {

        Application updatedApplication = applicationService.updateStatus(id, newStatus);
        return ResponseEntity.ok(updatedApplication);
    }

    // GET /api/applications/count/5
    @GetMapping("/count/{jobId}")
    public ResponseEntity<Long> getCount(@PathVariable Integer jobId) {
        long count = applicationService.countApplications(jobId);
        return ResponseEntity.ok(count);
    }
}