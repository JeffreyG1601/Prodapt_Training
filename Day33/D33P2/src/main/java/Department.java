import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Department {
    @Id @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST)
    private List<Employee> employees = new ArrayList<>();

    // Constructors, Getters, Setters...
    public Department() {}
    public Department(String name) { this.name = name; }
    public String getName() { return name; }
    public List<Employee> getEmployees() { return employees; }
    public void addEmployee(Employee emp) {
        this.employees.add(emp);
        emp.setDepartment(this);
    }
    @Override public String toString() { return "Department{id=" + id + ", name='" + name + "'}"; }
}