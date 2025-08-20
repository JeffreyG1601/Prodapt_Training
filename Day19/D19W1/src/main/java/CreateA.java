
import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateA")
public class CreateA extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CreateA() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String accno = request.getParameter("accno");
        String ifsc = request.getParameter("ifsc");
        String balance = request.getParameter("balance");
        String phone = request.getParameter("phone");

        Connection con = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Prodapt", "root", "root");

            String sql = "INSERT INTO ADetails (Id, Name, AccountNumber, IFSC, Balance, Phone) VALUES (?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ps.setString(2, name);
            ps.setString(3, accno);
            ps.setString(4, ifsc);
            ps.setDouble(5, Double.parseDouble(balance));
            ps.setString(6, phone);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                // ✅ Account created successfully → Go back to Home with success message
                response.sendRedirect("Home.html?msg=Account+Created+Successfully");
            } else {
                // ❌ Failed insertion → Go back to Home with error
                response.sendRedirect("Home.html?msg=Account+Creation+Failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
            // ❌ Error → Go back to Home with exception message
            response.sendRedirect("Home.html?msg=Error:+"
                    + e.getMessage().replace(" ", "+"));
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
