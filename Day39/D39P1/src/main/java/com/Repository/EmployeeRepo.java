package com.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    // you can add custom queries later if needed
}
