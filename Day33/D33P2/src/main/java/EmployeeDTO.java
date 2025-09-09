
public class EmployeeDTO {
    private String name;
    private String departmentName;

    public EmployeeDTO(String name, String departmentName) {
        this.name = name;
        this.departmentName = departmentName;
    }
    @Override public String toString() { return "EmployeeDTO{name='" + name + "', departmentName='" + departmentName + "'}"; }
}