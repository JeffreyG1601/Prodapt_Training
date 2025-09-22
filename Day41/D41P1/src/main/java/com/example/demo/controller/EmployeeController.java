package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.Emailservice;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private Emailservice emailservice;  // ✅ inject Emailservice bean
    @Autowired
    private SmsService smsService;
    // Endpoint: GET /employee/{id}
    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) {
        return employeeService.getEmployeeAndSendMail(id);
    }

    // Endpoint: GET /employee/test?to=someone@gmail.com
    @GetMapping("/test")
    public String sendTestMail(@RequestParam String to) {
        try {
            emailservice.sendSimpleEmail(
                    to,
                    "Test Mail from Spring Boot",
                    "Hello! This is a test mail from your Spring Boot application."
            );
            return "✅ Mail sent successfully to " + to;
        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Error sending mail: " + e.getMessage();
        }
    }
    @GetMapping("/sms")
    public String sendTestSms(@RequestParam String to) {
        try {
            smsService.sendSms(to, "Hello from Spring Boot via Twilio 🚀");
            return "✅ SMS sent to " + to;
        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Error sending SMS: " + e.getMessage();
        }
    }

}
