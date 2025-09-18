package com.example.demo.Service;



import com.example.demo.Model.EmployeeDTO;
import com.example.demo.Model.*;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public List<Employee> getAllEmployees() {
        return repo.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }

    public Employee createEmployee(EmployeeDTO dto) {
        Employee emp = new Employee();
        emp.setName(dto.getName());
        emp.setAge(dto.getAge());
        emp.setDesig(dto.getDesig());
        emp.setSal(dto.getSal());
        return repo.save(emp);
    }

    public Employee updateEmployee(Long id, EmployeeDTO dto) {
        Employee emp = getEmployeeById(id);
        emp.setName(dto.getName());
        emp.setAge(dto.getAge());
        emp.setDesig(dto.getDesig());
        emp.setSal(dto.getSal());
        return repo.save(emp);
    }

    public Employee patchEmployee(Long id, EmployeeDTO dto) {
        Employee emp = getEmployeeById(id);
        if (dto.getName() != null) emp.setName(dto.getName());
        if (dto.getAge() > 0) emp.setAge(dto.getAge());
        if (dto.getDesig() != null) emp.setDesig(dto.getDesig());
        if (dto.getSal() > 0) emp.setSal(dto.getSal());
        return repo.save(emp);
    }

    public void deleteEmployee(Long id) {
        Employee emp = getEmployeeById(id);
        repo.delete(emp);
    }
}
