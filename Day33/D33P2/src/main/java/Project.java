import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Project {
    @Id @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees = new HashSet<>();

    // Constructors, Getters, Setters...
    public Project() {}
    public Project(String name) { this.name = name; }
    public String getName() { return name; }
    @Override public String toString() { return "Project{id=" + id + ", name='" + name + "'}"; }
}