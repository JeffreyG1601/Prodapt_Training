package com.careercrafter.repository;

import com.careercrafter.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    // Corrected: traverse applicant object's id
    List<JobApplication> findByApplicant_Id(Long applicantId);

    // Corrected: traverse job object's employer's id
    List<JobApplication> findByJob_Employer_Id(Long employerId);
}
