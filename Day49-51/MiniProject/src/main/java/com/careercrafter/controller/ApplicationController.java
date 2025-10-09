package com.careercrafter.controller;

import com.careercrafter.model.JobApplication;
import com.careercrafter.repository.JobApplicationRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import java.util.stream.Collectors;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
public class ApplicationController {

    private final JobApplicationRepository appRepo;
    public ApplicationController(JobApplicationRepository appRepo){
        this.appRepo = appRepo;
    }

    @PostMapping
    public ResponseEntity<JobApplication> apply(@Valid @RequestBody JobApplication app){
        app.setAppliedAt(LocalDateTime.now());
        app.setStatus("APPLIED");
        JobApplication saved = appRepo.save(app);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/applicant/{applicantId}")
    public ResponseEntity<List<JobApplication>> byApplicant(@PathVariable Long applicantId){
        return ResponseEntity.ok(appRepo.findByApplicant_Id(applicantId));
    }

    @GetMapping("/employer/{employerId}")
    public ResponseEntity<List<JobApplication>> byEmployer(@PathVariable Long employerId){
        return ResponseEntity.ok(appRepo.findByJob_Employer_Id(employerId));
    }
}
