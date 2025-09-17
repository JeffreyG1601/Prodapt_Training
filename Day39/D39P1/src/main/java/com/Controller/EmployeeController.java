package com.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.Model.Employee;
import com.Repository.EmployeeRepo;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepo repo;

    // Get all employees
    @GetMapping(produces = {"application/json", "application/xml"})
    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    // Get employee by ID
    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public Employee getEmployeeById(@PathVariable int id) {
        Optional<Employee> emp = repo.findById(id);
        return emp.orElse(null);
    }

    // Add new employee
    @PostMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public Employee createEmployee(@RequestBody Employee emp) {
        return repo.save(emp);
    }

    // Update employee
    @PutMapping(value = "/{id}", produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee empDetails) {
        return repo.findById(id)
                .map(emp -> {
                    emp.setName(empDetails.getName());
                    emp.setAge(empDetails.getAge());
                    emp.setSalary(empDetails.getSalary());
                    emp.setDesig(empDetails.getDesig());
                    return repo.save(emp);
                })
                .orElse(null);
    }

    // Delete employee
    @DeleteMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public String deleteEmployee(@PathVariable int id) {
        repo.deleteById(id);
        return "Employee deleted with id: " + id;
    }
}
