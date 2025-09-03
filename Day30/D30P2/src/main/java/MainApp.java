import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MainApp {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();

        Scanner sc = new Scanner(System.in);
        Transaction tx = session.beginTransaction();

        // --- Take Employee input ---
        Employee emp = new Employee();
        System.out.print("Enter Employee ID: ");
        emp.setId(sc.nextInt());

        System.out.print("Enter Employee Name: ");
        emp.setName(sc.next());

        System.out.print("Enter Employee Age: ");
        emp.setAge(sc.nextInt());

        System.out.print("Enter Employee Salary: ");
        emp.setSalary(sc.nextInt());

        System.out.print("Enter Employee Designation: ");
        emp.setDesignation(sc.next());

        // --- EmployeeDetail ---
        EmployeeDetail detail = new EmployeeDetail();
        detail.setId(emp.getId()); // same ID for simplicity
        System.out.print("Enter Address: ");
        detail.setAddress(sc.next());
        System.out.print("Enter Phone: ");
        detail.setPhone(sc.next());
        emp.setDetail(detail);

        // --- Department ---
        Department dept = new Department();
        dept.setId(101);
        dept.setName("IT Department");
        emp.setDepartment(dept);

        // --- Projects ---
        Project p1 = new Project();
        p1.setId(201);
        p1.setTitle("AI Project");

        Project p2 = new Project();
        p2.setId(202);
        p2.setTitle("Cloud Project");

        Set<Project> projects = new HashSet<>();
        projects.add(p1);
        projects.add(p2);
        emp.setProjects(projects);

        // --- Save employee (cascades everything) ---
        session.save(emp);

        tx.commit();
        session.close();
        factory.close();

        System.out.println("✅ Employee & relationships saved successfully!");
    }
}
