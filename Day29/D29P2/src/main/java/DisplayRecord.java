import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class DisplayRecord {
    public static void main(String[] args, SessionFactory factory) {
        Session ses = factory.openSession();
        List<Employee> list = ses.createQuery("from Employee where designation = :d", Employee.class)
                                 .setParameter("d", args[0])
                                 .list();
        System.out.println("\n--- Employee Records (" + args[0] + ") ---");
        list.forEach(e -> System.out.println(
                e.getId() + " | " + e.getName() + " | " + e.getAge() +
                " | " + e.getSalary() + " | " + e.getDesignation()
        ));
        ses.close();
    }
}
