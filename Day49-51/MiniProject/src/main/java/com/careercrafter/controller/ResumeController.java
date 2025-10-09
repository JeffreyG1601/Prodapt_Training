package com.careercrafter.controller;

import com.careercrafter.model.Resume;
import com.careercrafter.repository.ResumeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/resumes")
public class ResumeController {

    private final ResumeRepository repo;
    public ResumeController(ResumeRepository repo){ this.repo = repo; }

    @PostMapping
    public ResponseEntity<Resume> uploadResume(@RequestBody Resume r) {
        r.setUploadedAt(LocalDateTime.now());
        Resume saved = repo.save(r);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<Resume>> getByOwner(@PathVariable Long ownerId){
        return ResponseEntity.ok(repo.findByOwnerId(ownerId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
