import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private final EmployeeRepository repo = new EmployeeRepository();

    public void createEmployee(Employee emp) {
        repo.save(emp);
        System.out.println("Employee created: " + emp);
    }

    public void updateSalary(int id, String designation, int newSalary) {
        Optional<Employee> empOpt = repo.findByIdAndDesignation(id, designation);
        if (empOpt.isPresent()) {
            Employee emp = empOpt.get();
            emp.setSalary(newSalary);
            System.out.println("Salary updated: " + emp);
        } else {
            System.out.println("No record found for that ID and designation");
        }
    }

    public void deleteEmployee(int id, String designation) {
        boolean removed = repo.deleteByIdAndDesignation(id, designation);
        if (removed) {
            System.out.println("Employee deleted with ID " + id);
        } else {
            System.out.println("No record found for that ID and designation");
        }
    }

    public void displayEmployees(String designation) {
        List<Employee> employees = repo.findAllByDesignation(designation);
        System.out.println("\n--- Employee Records (" + designation + ") ---");
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            employees.forEach(System.out::println);
        }
        System.out.println("============================");
    }
}
