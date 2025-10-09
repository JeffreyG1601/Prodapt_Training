package com.example.d54p12.service;

import com.example.d54p12.entity.Department;
import com.example.d54p12.repository.DepartmentRepository;
import com.example.d54p12.dto.Employee;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;
    private final WebClient webClient;

    public DepartmentService(DepartmentRepository repository, WebClient.Builder builder) {
        this.repository = repository;
        this.webClient = builder.baseUrl("http://D54P1-1").build();
    }

    public List<Department> getAllDepartments() {
        return repository.findAll();
    }

    public Department saveDepartment(Department dept) {
        return repository.save(dept);
    }

    public Employee getEmployeeById(Long id) {
        return webClient.get()
                .uri("/employees/{id}", id)
                .retrieve()
                .bodyToMono(Employee.class)
                .block();
    }
}
