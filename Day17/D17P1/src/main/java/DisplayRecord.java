import java.sql.*;

public class DisplayRecord {
    public static void main(String[] args) {
        try {
            String designation = args[0]; // received from MainMenu
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded..!");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
            System.out.println("Connection Created");

            PreparedStatement readStmt = con.prepareStatement(
                "SELECT * FROM Employee WHERE designation = ?");
            readStmt.setString(1, designation);
            ResultSet rs = readStmt.executeQuery();

            System.out.println("\n--- Employee Records (" + designation + ") ---");
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " | " +
                    rs.getString("name") + " | " +
                    rs.getInt("age") + " | " +
                    rs.getInt("salary") + " | " +
                    rs.getString("designation"));
            }
            System.out.println("============================");
            rs.close();
            readStmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
