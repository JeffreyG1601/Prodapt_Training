import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Scanner;

public class CreateRecord {
    public static void main(String[] args, EntityManagerFactory factory) {
        Scanner sc = new Scanner(System.in);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Employee emp = new Employee();
            System.out.print("Enter ID: ");
            emp.setId(sc.nextInt());
            System.out.print("Enter Name: ");
            emp.setName(sc.next());
            System.out.print("Enter Age: ");
            emp.setAge(sc.nextInt());
            System.out.print("Enter Salary: ");
            emp.setSalary(sc.nextInt());
            emp.setDesignation(args[0]);

            em.persist(emp); // JPA's equivalent of session.save()
            tx.commit();
            System.out.println("Record Inserted for " + args[0]);
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}