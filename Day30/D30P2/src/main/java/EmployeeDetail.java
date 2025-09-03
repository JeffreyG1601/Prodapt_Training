import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "employee_detail")
public class EmployeeDetail {
    @Id
    private int id;

    private String address;
    private String phone;
}
