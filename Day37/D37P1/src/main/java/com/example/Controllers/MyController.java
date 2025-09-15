package com.example.Controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Models.Employee;
@RestController
public class MyController {
	ArrayList<Employee> emp = new ArrayList<Employee>();
	public MyController() {
		emp.add(new Employee(101, 30, 30000, "Jeff", "Devops"));
		emp.add(new Employee(102, 31, 40000, "Varun", "Devsecops"));
		emp.add(new Employee(103, 32, 50000, "Brian", "Tester"));
	}
	@GetMapping("/employeeDetails")
	ArrayList<Employee> EmployeeDetails(){
		return emp;
	}
	@GetMapping("/employeeDetails/{id}")
	Employee getempbyid(@PathVariable("id") int id){
		for (Employee e : emp) {
			if(e.getId()== id) {
				return e;
			}
		}
		return null;
	}
	@PostMapping("/addemployee")
	String addemployee(@RequestBody Employee employee) {
		emp.add(employee);
		return "employee added";
	}
	@DeleteMapping("/Deletemployee/{id}")
	String delempbyid(@PathVariable("id") int id){
		for (Employee e : emp) {
			if(e.getId()== id) {
				emp.remove(e);
				return "Emplyee with id :"+id+" Deleted successfully";
			}
		}
		return "Employee not found";
	}
	@PutMapping("/updateemp")
	String updateemp(@RequestBody Employee updatemp) {
		for (Employee e : emp) {
			if(e.getId() == updatemp.getId()) {
				e.setName(updatemp.getName());
				e.setAge(updatemp.getAge());
				e.setDesig(updatemp.getDesig());
				e.setSalary(updatemp.getSalary());
				return "Employee updated Successfully";
			}
		}
		return "Employee not found";
	}
}
