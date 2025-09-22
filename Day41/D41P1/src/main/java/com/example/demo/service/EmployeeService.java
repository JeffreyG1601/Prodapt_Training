package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JavaMailSender mailSender;

    // Fetch employee by ID
    public Employee getEmployeeAndSendMail(Long id) {
        Optional<Employee> empOpt = employeeRepository.findById(id);
        if (empOpt.isPresent()) {
            Employee emp = empOpt.get();

            // Send Mail
            sendMail(emp);

            return emp;
        } else {
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }

    // Send Mail Method
    private void sendMail(Employee emp) {
        if (emp.getEmail() == null || emp.getEmail().isEmpty()) {
            throw new RuntimeException("No email found for employee: " + emp.getName());
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emp.getEmail());
        message.setSubject("Your Employee Details");
        message.setText("Hello " + emp.getName() + ",\n\n" +
                        "Here are your details:\n" +
                        "ID: " + emp.getId() + "\n" +
                        "Age: " + emp.getAge() + "\n" +
                        "Salary: " + emp.getSal() + "\n" +
                        "Designation: " + emp.getDesig() + "\n\n" +
                        "Regards,\nHR Team");

        mailSender.send(message);
    }
}
