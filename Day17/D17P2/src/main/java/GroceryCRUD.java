 
import java.sql.*;
import java.util.Scanner;
 
public class GroceryCRUD {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded..!");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Prodapt", "root", "root");
            System.out.println("Connection Created");
 
            while (true) {
                System.out.println("\n--- Grocery CRUD Menu ---");
                System.out.println("1. Add Grocery Item");
                System.out.println("2. View All Items");
                System.out.println("3. Update Item Price");
                System.out.println("4. Delete Item");
                System.out.println("5. Costliest Item");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
 
                switch (choice) {
                    case 1:
                        // CREATE
                        stmt = con.prepareStatement("INSERT INTO Grocery VALUES(?,?,?,?)");
                        System.out.print("Enter Item ID: ");
                        int id = sc.nextInt();
                        System.out.print("Enter Name: ");
                        String name = sc.next();
                        System.out.print("Enter Quantity: ");
                        int qty = sc.nextInt();
                        System.out.print("Enter Price: ");
                        double price = sc.nextDouble();
 
                        stmt.setInt(1, id);
                        stmt.setString(2, name);
                        stmt.setInt(3, qty);
                        stmt.setDouble(4, price);
                        stmt.executeUpdate();
                        System.out.println("Item added successfully!");
                        break;
 
                    case 2:
                        // READ
                        stmt = con.prepareStatement("SELECT * FROM Grocery");
                        rs = stmt.executeQuery();
                        System.out.println("\nID\tName\tQty\tPrice");
                        while (rs.next()) {
                            System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" +
                                    rs.getInt(3) + "\t" + rs.getDouble(4));
                        }
                        break;
 
                    case 3:
                        // UPDATE
                        stmt = con.prepareStatement("UPDATE Grocery SET price=? WHERE id=?");
                        System.out.print("Enter Item ID to update: ");
                        int uid = sc.nextInt();
                        System.out.print("Enter new Price: ");
                        double newPrice = sc.nextDouble();
                        stmt.setDouble(1, newPrice);
                        stmt.setInt(2, uid);
                        int rows = stmt.executeUpdate();
                        if (rows > 0) {
                            System.out.println("Price updated successfully!");
                        } else {
                            System.out.println("Item not found!");
                        }
                        break;
 
                    case 4:
                        // DELETE
                        stmt = con.prepareStatement("DELETE FROM Grocery WHERE id=?");
                        System.out.print("Enter Item ID to delete: ");
                        int did = sc.nextInt();
                        stmt.setInt(1, did);
                        int delRows = stmt.executeUpdate();
                        if (delRows > 0) {
                            System.out.println("Item deleted successfully!");
                        } else {
                            System.out.println("Item not found!");
                        }
                        break;
                    case 5:
                        stmt = con.prepareStatement("SELECT * FROM Grocery WHERE price = (SELECT MAX(price) FROM Grocery)");
                        rs = stmt.executeQuery();
                        System.out.println("\n--- Costliest Item ---");
                        System.out.println("ID\tName\tQty\tPrice");
                        boolean found = false;
                        while (rs.next()) {
                            found = true;
                            System.out.println(
                                rs.getInt(1) + "\t" +
                                rs.getString(2) + "\t" +
                                rs.getInt(3) + "\t" +
                                rs.getDouble(4)
                            );
                        }
                        if (!found) {
                            System.out.println("No items found in the table.");
                        }
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        sc.close();
                        if (stmt != null) stmt.close();
                        if (con != null) con.close();
                        return;
 
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
