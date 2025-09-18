package com.example.demo.Model;


import jakarta.validation.constraints.*;

public class EmployeeDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Min(value = 18, message = "Age must be >= 18")
    private int age;

    @NotBlank(message = "Designation cannot be blank")
    private String desig;

    @Positive(message = "Salary must be positive")
    private double sal;

    // Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getDesig() { return desig; }
    public void setDesig(String desig) { this.desig = desig; }

    public double getSal() { return sal; }
    public void setSal(double sal) { this.sal = sal; }
}
