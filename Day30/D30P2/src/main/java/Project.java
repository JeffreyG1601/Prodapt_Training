import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = "employees")
@Entity
@Table(name = "project")
public class Project {
    @Id
    private int id;

    private String title;

    // Many-to-Many
    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees;
}
