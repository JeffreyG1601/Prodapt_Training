package com.careercrafter.service;

import com.careercrafter.model.Job;
import com.careercrafter.repository.JobRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepo;

    public JobServiceImpl(JobRepository jobRepo){
        this.jobRepo = jobRepo;
    }

    @Override
    public Job createJob(Job job) {
        job.setPostedAt(LocalDateTime.now());
        return jobRepo.save(job);
    }

    @Override
    public Job updateJob(Long id, Job job) {
        Job existing = jobRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Job not found"));
        existing.setTitle(job.getTitle());
        existing.setDescription(job.getDescription());
        existing.setLocation(job.getLocation());
        existing.setIndustry(job.getIndustry());
        existing.setQualifications(job.getQualifications());
        existing.setEmploymentType(job.getEmploymentType());
        return jobRepo.save(existing);
    }

    @Override
    public void deleteJob(Long id) {
        jobRepo.deleteById(id);
    }

    @Override
    public Optional<Job> findById(Long id) {
        return jobRepo.findById(id);
    }

    @Override
    public List<Job> searchJobs(String title, String location, String industry) {
        Specification<Job> spec = Specification.where(null);

        if (StringUtils.hasText(title)) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%"));
        }
        if (StringUtils.hasText(location)) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("location")), "%" + location.toLowerCase() + "%"));
        }
        if (StringUtils.hasText(industry)) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("industry")), "%" + industry.toLowerCase() + "%"));
        }
        return jobRepo.findAll(spec);
    }

    @Override
    public List<Job> findAll() {
        return jobRepo.findAll();
    }
}
