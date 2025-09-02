import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.Scanner;

public class UpdateRecord {
    public static void main(String[] args, SessionFactory factory) {
        Scanner sc = new Scanner(System.in);
        Session ses = factory.openSession();
        Transaction tx = ses.beginTransaction();

        System.out.print("Enter ID to update: ");
        int id = sc.nextInt();
        Employee emp = ses.get(Employee.class, id);

        if (emp != null && emp.getDesignation().equals(args[0])) {
            System.out.print("Enter new Salary: ");
            emp.setSalary(sc.nextInt());
            ses.update(emp);
            tx.commit();
            System.out.println("Salary Updated for " + args[0]);
        } else {
            System.out.println("No record found for ID with designation " + args[0]);
            tx.rollback();
        }
        ses.close();
    }
}
