import java.sql.*;
import java.util.Scanner;

public class CRUD {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded..!");

            // Connect to MySQL
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Prodapt", "root", "root");
            System.out.println("Connection Created");

            while (true) {
                System.out.println("\n--- Employee CRUD Menu ---");
                System.out.println("1. Create (Insert Record)");
                System.out.println("2. Read (Display Records)");
                System.out.println("3. Update Record");
                System.out.println("4. Delete Record");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        // CREATE
                        PreparedStatement insertStmt = con.prepareStatement(
                                "INSERT INTO Employee VALUES(?,?,?,?,?)");
                        System.out.print("Enter Employee ID (eid): ");
                        int id = sc.nextInt();
                        System.out.print("Enter Name: ");
                        String name = sc.next();
                        System.out.print("Enter Age: ");
                        int age = sc.nextInt();
                        System.out.print("Enter Salary: ");
                        int salary = sc.nextInt();
                        System.out.print("Enter Designation: ");
                        String desig = sc.next();

                        insertStmt.setInt(1, id);
                        insertStmt.setString(2, name);
                        insertStmt.setInt(3, age);
                        insertStmt.setInt(4, salary);
                        insertStmt.setString(5, desig);

                        insertStmt.executeUpdate();
                        insertStmt.close();
                        System.out.println("Data Inserted Successfully!");
                        break;

                    case 2:
                        // READ
                        Statement readStmt = con.createStatement();
                        ResultSet rs = readStmt.executeQuery("SELECT * FROM Employee");
                        System.out.println("\n--- Employee Records ---");
                        while (rs.next()) {
                            System.out.println(
                                rs.getInt("eid") + " | " +
                                rs.getString("name") + " | " +
                                rs.getInt("age") + " | " +
                                rs.getInt("salary") + " | " +
                                rs.getString("design"));
                        }
                        rs.close();
                        readStmt.close();
                        break;

                    case 3:
                        // UPDATE
                        PreparedStatement updateStmt = con.prepareStatement(
                                "UPDATE Employee SET salary = ? WHERE eid = ?");
                        System.out.print("Enter Employee ID to Update Salary: ");
                        int updateId = sc.nextInt();
                        System.out.print("Enter New Salary: ");
                        int newSalary = sc.nextInt();

                        updateStmt.setInt(1, newSalary);
                        updateStmt.setInt(2, updateId);

                        int rowsUpdated = updateStmt.executeUpdate();
                        if (rowsUpdated > 0) {
                            System.out.println("Record Updated Successfully!");
                        } else {
                            System.out.println("No Employee Found with that ID!");
                        }
                        updateStmt.close();
                        break;

                    case 4:
                        // DELETE
                        PreparedStatement deleteStmt = con.prepareStatement(
                                "DELETE FROM Employee WHERE eid = ?");
                        System.out.print("Enter Employee ID to Delete: ");
                        int deleteId = sc.nextInt();

                        deleteStmt.setInt(1, deleteId); // FIXED: setting parameter

                        int rowsDeleted = deleteStmt.executeUpdate();
                        if (rowsDeleted > 0) {
                            System.out.println("Record Deleted Successfully!");
                        } else {
                            System.out.println("No Employee Found with that ID!");
                        }
                        deleteStmt.close();
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        con.close();
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid Choice! Please try again.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
