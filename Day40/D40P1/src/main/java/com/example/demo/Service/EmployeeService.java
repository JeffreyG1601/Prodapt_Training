package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Employee;
import com.example.demo.Repository.EmployeeRepo;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo repo;

    public List<Employee> getAllEmployees() {
        // could add sorting/filtering if needed
        return repo.findAll();
    }

    public ResponseEntity<Employee> getEmployeeById(int id) {
        return repo.findById(id)
                   .map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Employee> createEmployee(Employee emp) {
        if (emp.getId() == 0) { // manual ID safety if needed
            return ResponseEntity.badRequest().build();
        }
        Employee saved = repo.save(emp);
        return ResponseEntity.ok(saved);
    }

    public ResponseEntity<Employee> updateEmployee(int id, Employee empDetails) {
        return repo.findById(id)
                .map(emp -> {
                    emp.setName(empDetails.getName());
                    emp.setAge(empDetails.getAge());
                    emp.setSalary(empDetails.getSalary());
                    emp.setDesig(empDetails.getDesig());
                    Employee updated = repo.save(emp);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<String> deleteEmployee(int id) {
        return repo.findById(id)
                   .map(emp -> {
                       repo.delete(emp);
                       return ResponseEntity.ok("Employee deleted with id: " + id);
                   })
                   .orElse(ResponseEntity.notFound().build());
    }
}
