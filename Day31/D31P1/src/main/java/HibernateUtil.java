import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml") // should be in resources
                    .addAnnotatedClass(Employee.class)
                    .addAnnotatedClass(EmployeeProfile.class)
                    .addAnnotatedClass(Department.class)
                    .addAnnotatedClass(Project.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
