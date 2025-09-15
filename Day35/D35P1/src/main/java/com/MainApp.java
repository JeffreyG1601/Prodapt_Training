package com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

		// Constructor injection bean
		Employee e1 = (Employee) context.getBean("empConstructor");
		System.out.println("=== Constructor Injection Bean ===");
		System.out.println(e1);
		e1.display();

		// Setter injection bean
		Employee e2 = (Employee) context.getBean("empSetter");
		System.out.println("\n=== Setter Injection Bean ===");
		System.out.println(e2);
		e2.display();
	}
}
