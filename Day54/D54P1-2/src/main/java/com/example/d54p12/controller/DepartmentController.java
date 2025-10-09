package com.example.d54p12.controller;

import com.example.d54p12.entity.Department;
import com.example.d54p12.service.DepartmentService;
import com.example.d54p12.dto.Employee;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Department> all() {
        return service.getAllDepartments();
    }

    @PostMapping
    public Department save(@RequestBody Department dept) {
        return service.saveDepartment(dept);
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return service.getEmployeeById(id);
    }
}
