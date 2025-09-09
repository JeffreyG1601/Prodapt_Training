
import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPQL_PU");
        EntityManager em = emf.createEntityManager();

        try {
            setupData(em);

            System.out.println("\n--- 1. Select All Employees (Implicit) ---");
            List<Employee> allEmployees = em.createQuery("FROM Employee", Employee.class).getResultList();
            printResult(allEmployees);

            System.out.println("\n--- 2. Select All Employees (Explicit) ---");
            List<Employee> allEmployees2 = em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
            printResult(allEmployees2);
            
            System.out.println("\n--- 3. Select a Single Field (Employee Names) ---");
            List<String> names = em.createQuery("SELECT e.name FROM Employee e", String.class).getResultList();
            printResult(names);

            System.out.println("\n--- 4. Select Multiple Fields (Name and Salary) ---");
            List<Object[]> nameAndSalary = em.createQuery("SELECT e.name, e.salary FROM Employee e", Object[].class).getResultList();
            nameAndSalary.forEach(r -> System.out.println("  " + r[0] + ": " + r[1]));

            System.out.println("\n--- 5. Filtering with WHERE (Named Parameter) ---");
            TypedQuery<Employee> query5 = em.createQuery("SELECT e FROM Employee e WHERE e.name = :empName", Employee.class);
            query5.setParameter("empName", "Charlie");
            printResult(query5.getResultList());

            System.out.println("\n--- 6. Filtering with Salary > 80000 ---");
            List<Employee> highEarners = em.createQuery("SELECT e FROM Employee e WHERE e.salary > 80000", Employee.class).getResultList();
            printResult(highEarners);
            
            System.out.println("\n--- 7. Filtering with BETWEEN ---");
            List<Employee> midEarners = em.createQuery("SELECT e FROM Employee e WHERE e.salary BETWEEN 60000 AND 80000", Employee.class).getResultList();
            printResult(midEarners);
            
            System.out.println("\n--- 8. Filtering with IN ---");
            List<Employee> inList = em.createQuery("SELECT e FROM Employee e WHERE e.name IN ('Alice', 'Frank')", Employee.class).getResultList();
            printResult(inList);
            
            System.out.println("\n--- 9. Filtering with LIKE ---");
            List<Employee> likeList = em.createQuery("SELECT e FROM Employee e WHERE e.name LIKE 'D%'", Employee.class).getResultList();
            printResult(likeList);
            
            System.out.println("\n--- 10. Filtering with IS NULL (Employees with no Parking) ---");
            List<Employee> noParking = em.createQuery("SELECT e FROM Employee e WHERE e.parkingSpace IS NULL", Employee.class).getResultList();
            printResult(noParking);
            
            System.out.println("\n--- 11. Implicit Join (Employees in 'Engineering') ---");
            TypedQuery<Employee> query11 = em.createQuery("SELECT e FROM Employee e WHERE e.department.name = 'Engineering'", Employee.class);
            printResult(query11.getResultList());
            
            System.out.println("\n--- 12. Explicit INNER JOIN ---");
            TypedQuery<Employee> query12 = em.createQuery("SELECT e FROM Employee e JOIN e.department d WHERE d.name = 'HR'", Employee.class);
            printResult(query12.getResultList());
            
            System.out.println("\n--- 13. LEFT JOIN (All Depts and their Employees) ---");
            List<Object[]> leftJoinResult = em.createQuery("SELECT d.name, e.name FROM Department d LEFT JOIN d.employees e", Object[].class).getResultList();
            leftJoinResult.forEach(r -> System.out.println("  " + r[0] + " -> " + (r[1] == null ? "NULL" : r[1])));

            System.out.println("\n--- 14. ManyToMany Join (Employees on 'Phoenix' Project) ---");
            TypedQuery<Employee> query14 = em.createQuery("SELECT e FROM Employee e JOIN e.projects p WHERE p.name = 'Project Phoenix'", Employee.class);
            printResult(query14.getResultList());
            
            System.out.println("\n--- 15. Aggregate - COUNT ---");
            Long empCount = em.createQuery("SELECT COUNT(e) FROM Employee e", Long.class).getSingleResult();
            System.out.println("  Total Employees: " + empCount);
            
            System.out.println("\n--- 16. Aggregate - AVG ---");
            Double avgSalary = em.createQuery("SELECT AVG(e.salary) FROM Employee e", Double.class).getSingleResult();
            System.out.println("  Average Salary: " + avgSalary);
            
            System.out.println("\n--- 17. Aggregate - MAX ---");
            Integer maxSalary = em.createQuery("SELECT MAX(e.salary) FROM Employee e", Integer.class).getSingleResult();
            System.out.println("  Maximum Salary: " + maxSalary);
            
            System.out.println("\n--- 18. GROUP BY (Employee count per department) ---");
            List<Object[]> groupByResult = em.createQuery("SELECT d.name, COUNT(e) FROM Department d JOIN d.employees e GROUP BY d.name", Object[].class).getResultList();
            groupByResult.forEach(r -> System.out.println("  " + r[0] + ": " + r[1]));

            System.out.println("\n--- 19. HAVING (Departments with more than 2 employees) ---");
            List<String> havingResult = em.createQuery("SELECT d.name FROM Department d JOIN d.employees e GROUP BY d.name HAVING COUNT(e) > 2", String.class).getResultList();
            printResult(havingResult);
            
            System.out.println("\n--- 20. ORDER BY (Employees by salary descending) ---");
            List<Employee> orderedEmployees = em.createQuery("SELECT e FROM Employee e ORDER BY e.salary DESC", Employee.class).getResultList();
            printResult(orderedEmployees);
            
            System.out.println("\n--- 21. Constructor Expression (Custom DTO) ---");
            List<EmployeeDTO> dtoList = em.createQuery("SELECT NEW EmployeeDTO(e.name, d.name) FROM Employee e JOIN e.department d", EmployeeDTO.class).getResultList();
            printResult(dtoList);

            // DML Statements (UPDATE and DELETE) must be in a transaction
            em.getTransaction().begin();
            System.out.println("\n--- 22. Bulk UPDATE (Give a 10% raise to engineers) ---");

            // THE FIX IS HERE:
            Query updateQuery = em.createQuery(
                "UPDATE Employee e SET e.salary = e.salary * 1.1 " +
                "WHERE e.department IN (SELECT d FROM Department d WHERE d.name = 'Engineering')"
            );
            
            int updatedCount = updateQuery.executeUpdate();
            System.out.println("  Updated " + updatedCount + " employees.");

            System.out.println("\n--- 23. Bulk DELETE (Remove employees with salary < 60000) ---");
            // Note: This delete query was already correct as it didn't use a join
            Query deleteQuery = em.createQuery("DELETE FROM Employee e WHERE e.salary < 60000");
            int deletedCount = deleteQuery.executeUpdate();
            System.out.println("  Deleted " + deletedCount + " employees.");
            em.getTransaction().commit();

            System.out.println("\n--- Final Employee List after DML ---");
            List<Employee> finalList = em.createQuery("FROM Employee", Employee.class).getResultList();
            printResult(finalList);

        } finally {
            em.close();
            emf.close();
        }
    }

    private static <T> void printResult(List<T> resultList) {
        if (resultList == null || resultList.isEmpty()) {
            System.out.println("  No results found.");
            return;
        }
        for (T item : resultList) {
            System.out.println("  " + item.toString());
        }
    }
    
    // Helper method to populate the database with test data
    private static void setupData(EntityManager em) {
        em.getTransaction().begin();

        Department hr = new Department("HR");
        Department engineering = new Department("Engineering");
        Department marketing = new Department("Marketing");

        Employee alice = new Employee("Alice", 70000);
        Employee bob = new Employee("Bob", 80000);
        Employee charlie = new Employee("Charlie", 90000);
        Employee david = new Employee("David", 120000);
        Employee eve = new Employee("Eve", 55000);
        Employee frank = new Employee("Frank", 65000);

        hr.addEmployee(alice);
        hr.addEmployee(eve);
        engineering.addEmployee(bob);
        engineering.addEmployee(charlie);
        engineering.addEmployee(david);
        marketing.addEmployee(frank);

        alice.setParkingSpace(new ParkingSpace(101));
        charlie.setParkingSpace(new ParkingSpace(202));
        david.setParkingSpace(new ParkingSpace(203));

        Project phoenix = new Project("Project Phoenix");
        Project titan = new Project("Project Titan");

        alice.addProject(titan);
        bob.addProject(phoenix);
        charlie.addProject(phoenix);
        david.addProject(phoenix);
        frank.addProject(titan);
        
        // Persist departments, which cascades to employees, parking spaces, and projects
        em.persist(hr);
        em.persist(engineering);
        em.persist(marketing);
        em.persist(phoenix);
        em.persist(titan);

        em.getTransaction().commit();
    }
}