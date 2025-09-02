import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="HibernateEmployee")
public class Employee {
	@Id
	private int id;
	@Column(name="ename")
	private String name;
	private int age;
	private int salary;
	private String designation;
}
