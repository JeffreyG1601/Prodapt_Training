package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.repo.EmployeeRepo;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepository;

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Create/save employee
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Update employee
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        return employeeRepository.findById(id)
                .map(emp -> {
                    emp.setName(updatedEmployee.getName());
                    emp.setDesign(updatedEmployee.getDesign());
                    emp.setAge(updatedEmployee.getAge());
                    emp.setSalary(updatedEmployee.getSalary());
                    return employeeRepository.save(emp);
                })
                .orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    // Delete employee
    public void deleteEmployee(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found with id " + id);
        }
        employeeRepository.deleteById(id);
    }
}
