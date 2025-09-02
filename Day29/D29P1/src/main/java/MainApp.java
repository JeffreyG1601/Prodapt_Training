import org.hibernate.*;
import org.hibernate.cfg.Configuration;
public class MainApp {
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory fact = cfg.buildSessionFactory();
		Session ses = fact.openSession();
		Transaction tx = ses.beginTransaction();
		Employee e = new Employee();
		e.setId(101);
		e.setAge(20);
		e.setName("Jeff");
		e.setSalary(10000);
		e.setDesignation("Developer");
		ses.save(e);
		tx.commit();
		System.out.println("beep saved beep");
		
	}
}
