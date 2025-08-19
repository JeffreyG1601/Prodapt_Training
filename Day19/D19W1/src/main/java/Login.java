import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Login() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Prodapt", "root", "root");

            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM login WHERE Name=? AND password=?");
            ps.setString(1, uname);
            ps.setString(2, pwd);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // ✅ Successful login → redirect to Home.html
                response.sendRedirect("Home.html");
            } else {
                // ❌ Failed login → redirect back with error
                response.sendRedirect("Login.html?error=Invalid+username+or+password");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            // If DB error occurs, send error back to login page
            response.sendRedirect("Login.html?error=" + e.getMessage());
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doPost(request, response);
    }
}
