import java.sql.*;
import java.util.Scanner;

public class DeleteRecord {
    public static void main(String[] args) {
        try {
            String designation = args[0]; // received from MainMenu
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded..!");
            Scanner sc = new Scanner(System.in);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
            System.out.println("Connection made..!");
            PreparedStatement del = con.prepareStatement(
                "delete from employee where id = ? and designation = ?");
            
            System.out.println("Enter ID to be deleted");
            int id = sc.nextInt();
            System.out.println("Confirm Choice:-(Y/N)");
            String ch = sc.next();
            if (ch.equalsIgnoreCase("yes") || ch.equalsIgnoreCase("y")) {
                del.setInt(1, id);
                del.setString(2, designation);
                int rows = del.executeUpdate();
                if (rows > 0) {
                    System.out.println("Data Deleted ");
                } else {
                    System.out.println("No record found for that ID and designation");
                }
            } else {
                System.out.println("Data Not Deleted");
            }
            del.close();
        } catch (Exception e) {
            System.out.println("Enter Valid ID");
        }
    }
}
