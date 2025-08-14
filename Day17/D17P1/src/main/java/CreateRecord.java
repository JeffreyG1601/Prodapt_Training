import java.sql.*;
import java.util.Scanner;
public class CreateRecord {
	public static void main(String[] args) {
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
	         System.out.println("Driver loaded..!");
	         String designation = args[0];
	         Scanner sc = new Scanner(System.in);
	         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
	         System.out.println("Connection made..!");
	         System.out.println("Enter number of records to be entered:-");
	         int count = sc.nextInt();
	         PreparedStatement ins =con.prepareStatement("insert into employee values (?,?,?,?,?);");
	         while(count!=0) {
	        	 System.out.println("============================");
	         System.out.print("Enter Employee ID (eid): ");
             int id = sc.nextInt();
             System.out.print("Enter Name: ");
             String name = sc.next();
             System.out.print("Enter Age: ");
             int age = sc.nextInt();
             System.out.print("Enter Salary: ");
             int salary = sc.nextInt();
             System.out.println("============================");
             ins.setInt(1, id);
             ins.setString(2, name);
             ins.setInt(3, age);
             ins.setInt(4, salary);
             ins.setString(5, designation);
             ins.executeUpdate();
             count-=1;
	         }
             ins.close();
             System.out.println("============================");
             System.out.println("Data Inserted Successfully!");
             System.out.println("============================");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}