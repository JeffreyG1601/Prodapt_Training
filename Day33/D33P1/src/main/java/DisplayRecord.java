import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class DisplayRecord {
    public static void main(String[] args, EntityManagerFactory factory) {
        EntityManager em = factory.createEntityManager();
        try {
            // Using JPQL (Java Persistence Query Language) which is very similar to HQL
            TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e WHERE e.designation = :d", Employee.class);
            query.setParameter("d", args[0]);
            List<Employee> list = query.getResultList();

            System.out.println("\n--- Employee Records (" + args[0] + ") ---");
            list.forEach(e -> System.out.println(
                    e.getId() + " | " + e.getName() + " | " + e.getAge() +
                    " | " + e.getSalary() + " | " + e.getDesignation()
            ));
        } finally {
            em.close();
        }
    }
}