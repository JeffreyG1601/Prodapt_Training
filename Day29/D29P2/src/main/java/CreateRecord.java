import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.Scanner;

public class CreateRecord {
    public static void main(String[] args, SessionFactory factory) {
        Scanner sc = new Scanner(System.in);
        Session ses = factory.openSession();
        Transaction tx = ses.beginTransaction();

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

        ses.save(emp);
        tx.commit();
        ses.close();
        System.out.println("Record Inserted for " + args[0]);
    }
}
