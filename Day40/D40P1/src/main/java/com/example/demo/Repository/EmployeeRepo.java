package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    // Extra queries can go here if needed
}
