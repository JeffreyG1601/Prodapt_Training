package com.example.demo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Min(value = 18, message = "Age must be >= 18")
    private int age;

    @NotBlank(message = "Designation cannot be blank")
    private String desig;

    @Positive(message = "Salary must be positive")
    private double sal;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getDesig() { return desig; }
    public void setDesig(String desig) { this.desig = desig; }

    public double getSal() { return sal; }
    public void setSal(double sal) { this.sal = sal; }
}
