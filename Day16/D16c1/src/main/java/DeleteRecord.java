import java.sql.*;
import java.util.Scanner;
public class DeleteRecord {
	public static void main(String[] args) {
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
	         System.out.println("Driver loaded..!");
	         Scanner sc = new Scanner(System.in);
	         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Prodapt", "root", "root");
	         System.out.println("Connection made..!");
	         PreparedStatement del =con.prepareStatement("delete from employee where eid = ?");
	         System.out.println("Enter ID to be deleted");
	         int id = sc.nextInt();
	         System.out.println("Confirm Choice:-(Y/N)");
	         String ch =sc.next();
	         if(ch.equalsIgnoreCase("yes")||ch.equalsIgnoreCase("y")) {
	        	 del.setInt(1, id);
	        	 del.execute();
	        	 System.out.println("Data Deleted ");
	         }
	         else {
	        	 System.out.println("Data Not Deleted");
	         }
	         sc.close();del.close();
}
		catch(Exception e) {
			System.out.println("Enter Valid ID");
		}
	}
}
