package com.careercrafter.controller;

import com.careercrafter.model.Job;
import com.careercrafter.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {

    private final JobService jobService;
    public JobController(JobService jobService){ this.jobService = jobService; }

    @GetMapping
    public ResponseEntity<List<Job>> listJobs(@RequestParam(required=false) String title,
                                              @RequestParam(required=false) String location,
                                              @RequestParam(required=false) String industry){
        return ResponseEntity.ok(jobService.searchJobs(title, location, industry));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJob(@PathVariable Long id){
        return jobService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Employers create job
    @PostMapping
    public ResponseEntity<Job> createJob(@Valid @RequestBody Job job, Authentication auth) {
        // bind employer: load user by email from auth.getName() or service - simplified here
        // In production: fetch User entity and set as employer
        Job saved = jobService.createJob(job);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @Valid @RequestBody Job job) {
        Job updated = jobService.updateJob(id, job);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }
}
