import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class D30j1 {
    public static void main(String[] args) {
        // Load configuration
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml"); // must be in classpath
        SessionFactory fact = cfg.buildSessionFactory();
        Session ses = fact.openSession();
        Transaction tx = ses.beginTransaction();

        System.out.println("\n--- Executing HQL Queries ---");

        // 1. Fetch all employees
        List<Employee> q1 = ses.createQuery("from Employee", Employee.class).list();
        System.out.println("\n1. All Employees:");
        q1.forEach(System.out::println);

        // 2. Fetch only employee names (Projection)
        List<String> q2 = ses.createQuery("select e.name from Employee e", String.class).list();
        System.out.println("\n2. Employee Names:");
        q2.forEach(System.out::println);

        // 3. Employees with salary greater than 30000
        List<Employee> q3 = ses.createQuery("from Employee e where e.salary > 30000", Employee.class).list();
        System.out.println("\n3. Employees with salary > 30000:");
        q3.forEach(System.out::println);

        // 4. Employees ordered by name
        List<Employee> q4 = ses.createQuery("from Employee e order by e.name", Employee.class).list();
        System.out.println("\n4. Employees ordered by name:");
        q4.forEach(System.out::println);

        // 5. Distinct designations
        List<String> q5 = ses.createQuery("select distinct e.designation from Employee e", String.class).list();
        System.out.println("\n5. Distinct Designations:");
        q5.forEach(System.out::println);

        // 6. Count number of employees
        Long count = ses.createQuery("select count(e.id) from Employee e", Long.class).uniqueResult();
        System.out.println("\n6. Total Employees: " + count);

        // 7. Average salary of employees
        Double avgSalary = ses.createQuery("select avg(e.salary) from Employee e", Double.class).uniqueResult();
        System.out.println("\n7. Average Salary: " + avgSalary);

        // 8. Employees whose name starts with 'A'
        List<Employee> q8 = ses.createQuery("from Employee e where e.name like 'A%'", Employee.class).list();
        System.out.println("\n8. Employees whose name starts with 'A':");
        q8.forEach(System.out::println);

        // 9. Top 3 highest paid employees (Pagination + Order by)
        List<Employee> q9 = ses.createQuery("from Employee e order by e.salary desc", Employee.class)
                               .setMaxResults(3)
                               .list();
        System.out.println("\n9. Top 3 Highest Paid Employees:");
        q9.forEach(System.out::println);

        // 10. Group employees by designation and count them
        List<Object[]> q10 = ses.createQuery(
            "select e.designation, count(e.id) from Employee e group by e.designation",
            Object[].class
        ).list();
        System.out.println("\n10. Employee count by Designation:");
        for (Object[] row : q10) {
            System.out.println("Designation: " + row[0] + ", Count: " + row[1]);
        }

        tx.commit();
        ses.close();
        fact.close();
    }
}
