package com.careercrafter.service;

import com.careercrafter.model.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {
    Job createJob(Job job);
    Job updateJob(Long id, Job job);
    void deleteJob(Long id);
    Optional<Job> findById(Long id);
    List<Job> searchJobs(String title, String location, String industry);
    List<Job> findAll();
}
