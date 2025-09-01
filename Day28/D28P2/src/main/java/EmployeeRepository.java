import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository {
    private static final List<Employee> employees = new ArrayList<>();

    public void save(Employee emp) {
        employees.add(emp);
    }

    public List<Employee> findAllByDesignation(String designation) {
        List<Employee> result = new ArrayList<>();
        for (Employee emp : employees) {
            if (emp.getDesignation().equalsIgnoreCase(designation)) {
                result.add(emp);
            }
        }
        return result;
    }

    public Optional<Employee> findByIdAndDesignation(int id, String designation) {
        return employees.stream()
                .filter(e -> e.getId() == id && e.getDesignation().equalsIgnoreCase(designation))
                .findFirst();
    }

    public boolean deleteByIdAndDesignation(int id, String designation) {
        return employees.removeIf(e -> e.getId() == id && e.getDesignation().equalsIgnoreCase(designation));
    }
}
