package com.careercrafter.repository;

import com.careercrafter.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findByOwnerId(Long ownerId);
}
