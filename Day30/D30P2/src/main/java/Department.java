import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "employees")
@Entity
@Table(name = "department")
public class Department {
    @Id
    private int id;

    private String name;

    // One-to-Many
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees;
}
