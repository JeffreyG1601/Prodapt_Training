import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainApp {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // 1:1 Unidirectional
        System.out.println("=== 1:1 Unidirectional ===");
        Department d1 = Department.builder().deptName("HR").build();
        Employee e1 = Employee.builder().empName("Alice").build();
        d1.setEmployeeOneToOneUni(e1);
        session.save(e1);
        session.save(d1);

        // 1:1 Bidirectional
        System.out.println("=== 1:1 Bidirectional ===");
        Department d2 = Department.builder().deptName("Finance").build();
        Employee e2 = Employee.builder().empName("Bob").departmentBi(d2).build();
        d2.setEmployeeOneToOneBi(e2);
        session.save(d2);
        session.save(e2);

        // 1:M Unidirectional
        System.out.println("=== 1:M Unidirectional ===");
        Department d3 = Department.builder().deptName("IT").build();
        Employee e3 = Employee.builder().empName("Charlie").build();
        Employee e4 = Employee.builder().empName("David").build();
        d3.getEmployeesUni().add(e3);
        d3.getEmployeesUni().add(e4);
        session.save(e3);
        session.save(e4);
        session.save(d3);

        // 1:M Bidirectional
        System.out.println("=== 1:M Bidirectional ===");
        Department d4 = Department.builder().deptName("Admin").build();
        Employee e5 = Employee.builder().empName("Eve").departmentBiMany(d4).build();
        Employee e6 = Employee.builder().empName("Frank").departmentBiMany(d4).build();
        d4.getEmployeesBi().add(e5);
        d4.getEmployeesBi().add(e6);
        session.save(d4);
        session.save(e5);
        session.save(e6);

        // M:1 Unidirectional
        System.out.println("=== M:1 Unidirectional ===");
        Department d5 = Department.builder().deptName("Legal").build();
        Employee e7 = Employee.builder().empName("Grace").departmentUniMany(d5).build();
        session.save(d5);
        session.save(e7);

        // M:M
        System.out.println("=== M:M ===");
        Department d6 = Department.builder().deptName("R&D").build();
        Department d7 = Department.builder().deptName("Sales").build();
        Employee e8 = Employee.builder().empName("Hank").build();
        e8.getDepartments().add(d6);
        e8.getDepartments().add(d7);
        session.save(d6);
        session.save(d7);
        session.save(e8);

        tx.commit();
        session.close();
        HibernateUtil.shutdown();
        System.out.println("=== All mappings tested successfully ===");
    }
}
