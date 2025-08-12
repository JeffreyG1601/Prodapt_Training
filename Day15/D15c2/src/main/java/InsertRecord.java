
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
 
public class InsertRecord {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded..!");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Prodapt", "root","root");
			System.out.println("Connection Created");
			PreparedStatement stmt= con.prepareStatement("insert into Employee values(?,?,?,?,?)");
			Scanner sc = new Scanner(System.in);
			System.out.println("ID "); int id= sc.nextInt();
			System.out.println("NAME :");String name= sc.next();
			System.out.println("Age :"); int age = sc.nextInt();
			System.out.println("Salary :"); int salary = sc.nextInt();
			System.out.println("Designation "); String desig= sc.next();
			stmt.setInt(1,id);
			stmt.setString(2, name);
			stmt.setInt(3, age);
			stmt.setInt(4, salary);
			stmt.setString(5, desig);
			stmt.execute();
			stmt.close();
			System.out.println("Data Inserted Successfully.....!");
			
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
}