package D26p1;
import java.io.*;
import java.util.ArrayList;

public class EmployeeManager {
    private ArrayList<employee> employees = new ArrayList<>();
    private final String filePath = "Employee.txt";

    // Constructor to load data from the file
    public EmployeeManager() {
        loadFromFile();
    }

    public void addEmployee(employee employee) {
        employees.add(employee);
        saveToFile();
    }

    public void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            for (employee employee : employees) {
                System.out.println(employee);
            }
        }
    }

    public void removeEmployee(int id) {
        boolean removed = employees.removeIf(employee -> employee.getId() == id);
        if (removed) {
            System.out.println("Employee with ID " + id + " removed.");
            saveToFile();
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    public void searchEmployee(int id) {
        for (employee employee : employees) {
            if (employee.getId() == id) {
                System.out.println("Employee Found: " + employee);
                return;
            }
        }
        System.out.println("Employee with ID " + id + " not found.");
    }

    // Save employees to file using PrintWriter
    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (employee employee : employees) {
                writer.println(employee.getId() + "," +
                        employee.getName() + "," +
                        employee.getDepartment() + "," +
                        employee.getSalary());
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    // Load employees from file (still uses BufferedReader for efficiency)
    private void loadFromFile() {
        File file = new File(filePath);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String department = parts[2];
                double salary = Double.parseDouble(parts[3]);
                employees.add(new employee(id, name, department, salary));
            }
        } catch (IOException e) {
            System.out.println("Error loading from file: " + e.getMessage());
        }
    }
}
