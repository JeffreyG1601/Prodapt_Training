package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int age;
    private double sal;
    private String name;
    private String desig;

    private String email; // Required to send mail

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public double getSal() { return sal; }
    public void setSal(double sal) { this.sal = sal; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDesig() { return desig; }
    public void setDesig(String desig) { this.desig = desig; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
