import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.Scanner;

public class DeleteRecord {
    public static void main(String[] args, SessionFactory factory) {
        Scanner sc = new Scanner(System.in);
        Session ses = factory.openSession();
        Transaction tx = ses.beginTransaction();

        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt();
        Employee emp = ses.get(Employee.class, id);

        if (emp != null && emp.getDesignation().equals(args[0])) {
            ses.delete(emp);
            tx.commit();
            System.out.println("Record Deleted for " + args[0]);
        } else {
            System.out.println("No record found for ID with designation " + args[0]);
            tx.rollback();
        }
        ses.close();
    }
}
