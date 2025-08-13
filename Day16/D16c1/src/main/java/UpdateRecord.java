import java.sql.*;
import java.util.Scanner;
public class UpdateRecord {
	public static void main(String[] args) {
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
	         System.out.println("Driver loaded..!");
	         Scanner sc = new Scanner(System.in);
	         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Prodapt", "root", "root");
	         System.out.println("Connection made..!");
	         PreparedStatement upd =con.prepareStatement("update employee set salary = ? where eid = ?");
	         System.out.println("Enter ID to be updated");
	         int id = sc.nextInt();
	         System.out.println("Confirm Choice:-(Y/N)");
	         String ch =sc.next();
	         if(ch.equalsIgnoreCase("yes")||ch.equalsIgnoreCase("y")) {
	        	 System.out.println("Enter the new salary:-");
	        	 int sal =sc.nextInt();
	        	 upd.setInt(2, id);
	        	 upd.setInt(1, sal);
	        	 upd.execute();
	        	 System.out.println("Salary updated");
		}
	         else {
	        	 System.out.println("Data Not Deleted");
	         }
	         sc.close();upd.close();
}
	catch(Exception e) {
		System.out.println("Enter Valid ID");
	}
	}
}
