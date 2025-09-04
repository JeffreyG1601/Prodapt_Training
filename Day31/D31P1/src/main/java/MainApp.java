
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // Create Department
        Department dept = Department.builder().deptName("IT").build();

        // Create EmployeeProfile
        EmployeeProfile profile = EmployeeProfile.builder()
                .address("123 Street")
                .phone("9876543210")
                .build();

        // Create Employee
        Employee emp = Employee.builder()
                .name("John Doe")
                .profile(profile)       // One-to-One
                .department(dept)       // Many-to-One
                .build();

        dept.getEmployees().add(emp);  // maintain bidirectional consistency

        // Create Project
        Project project = Project.builder().projectName("Hibernate Migration").build();
        emp.getProjects().add(project);
        project.getEmployees().add(emp);

        // Persist all
        session.persist(profile);
        session.persist(dept);
        session.persist(project);
        session.persist(emp);

        tx.commit();

        // Fetch back to show mapping
        Employee fetched = session.get(Employee.class, emp.getId());
        System.out.println("Employee: " + fetched.getName());
        System.out.println("Profile: " + fetched.getProfile().getAddress());
        System.out.println("Department: " + fetched.getDepartment().getDeptName());
        System.out.println("Projects: " + fetched.getProjects().stream().map(Project::getProjectName).toList());

        session.close();
        HibernateUtil.getSessionFactory().close();
    }
}
