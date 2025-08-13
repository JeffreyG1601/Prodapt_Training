import java.sql.*;
import java.util.Scanner;
public class DisplayRecord {
	public static void main(String[] args) {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded..!");

        // Connect to MySQL
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Prodapt", "root", "root");
        System.out.println("Connection Created");
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
	}
		catch(Exception e) {
			System.out.println("Beep Boop error");
		}
}}
