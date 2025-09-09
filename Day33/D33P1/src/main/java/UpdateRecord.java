import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Scanner;

public class UpdateRecord {
    public static void main(String[] args, EntityManagerFactory factory) {
        Scanner sc = new Scanner(System.in);
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            System.out.print("Enter ID to update: ");
            int id = sc.nextInt();
            Employee emp = em.find(Employee.class, id); // JPA's equivalent of session.get()

            if (emp != null && emp.getDesignation().equals(args[0])) {
                System.out.print("Enter new Salary: ");
                emp.setSalary(sc.nextInt());
                // No explicit update call needed in JPA for managed entities
                tx.commit();
                System.out.println("Salary Updated for " + args[0]);
            } else {
                System.out.println("No record found for ID with designation " + args[0]);
                tx.rollback();
            }
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