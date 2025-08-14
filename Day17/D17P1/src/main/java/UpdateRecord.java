import java.sql.*;
import java.util.Scanner;

public class UpdateRecord {
    public static void main(String[] args) {
        try {
            String designation = args[0]; // received from MainMenu
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded..!");
            Scanner sc = new Scanner(System.in);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
            System.out.println("Connection made..!");
            PreparedStatement upd = con.prepareStatement(
                "update employee set salary = ? where id = ? and designation = ?");
            System.out.println("============================");
            System.out.println("Enter ID to be updated");
            int id = sc.nextInt();
            System.out.println("Confirm Choice:-(Y/N)");
            String ch = sc.next();
            if (ch.equalsIgnoreCase("yes") || ch.equalsIgnoreCase("y")) {
                System.out.println("Enter the new salary:-");
                int sal = sc.nextInt();
                upd.setInt(1, sal);
                upd.setInt(2, id);
                upd.setString(3, designation);
                int rows = upd.executeUpdate();
                if (rows > 0) {
                    System.out.println("Salary updated");
                    System.out.println("============================");
                } else {
                    System.out.println("No record found for that ID and designation");
                }
            } else {
                System.out.println("Data Not Updated");
            }
            upd.close();
        } catch (Exception e) {
            System.out.println("Enter Valid ID");
        }
    }
}
