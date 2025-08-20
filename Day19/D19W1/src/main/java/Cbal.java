import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Cbal extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String ifsc = request.getParameter("ifsc");
        String accno = request.getParameter("accno");
        String phone = request.getParameter("phone");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Prodapt", "root", "root");

            String query = "";
            PreparedStatement stmt = null;

            if (accno != null && !accno.isEmpty()) {
                query = "SELECT balance FROM accounts WHERE ifsc=? AND accno=?";
                stmt = con.prepareStatement(query);
                stmt.setString(1, ifsc);
                stmt.setString(2, accno);
            } else if (phone != null && !phone.isEmpty()) {
                query = "SELECT balance FROM accounts WHERE ifsc=? AND phone=?";
                stmt = con.prepareStatement(query);
                stmt.setString(1, ifsc);
                stmt.setString(2, phone);
            } else {
                out.println("<h3 style='color:red;'>Please provide either Account Number or Phone Number.</h3>");
                return;
            }

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double balance = rs.getDouble("balance");
                out.println("<h2 style='color:green;'>Account Balance: ₹" + balance + "</h2>");
            } else {
                out.println("<h3 style='color:red;'>Invalid details. Please try again.</h3>");
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            out.println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
            e.printStackTrace();
        }
    }
}
