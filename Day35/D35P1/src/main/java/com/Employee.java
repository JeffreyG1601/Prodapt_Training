package com;

public class Employee {
	private int uid;
	private String name;
	private String city;
	private int ph;

	// Constructor (for constructor injection)
	public Employee(int uid, String name, String city, int ph) {
		this.uid = uid;
		this.name = name;
		this.city = city;
		this.ph = ph;
	}

	// Default constructor (for setter injection)
	public Employee() {}

	// Setters (for setter injection)
	public void setUid(int uid) {
		this.uid = uid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setPh(int ph) {
		this.ph = ph;
	}

	@Override
	public String toString() {
		return "Employee [uid=" + uid + ", name=" + name + ", city=" + city + ", ph=" + ph + "]";
	}

	public void display() {
		System.out.println("ID :" + uid);
		System.out.println("Name :" + name);
		System.out.println("City :" + city);
		System.out.println("Phone :" + ph);
	}
}
