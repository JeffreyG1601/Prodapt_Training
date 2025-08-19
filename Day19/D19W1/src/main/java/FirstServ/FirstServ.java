package FirstServ;

import java.io.PrintWriter;
import java.sql.DriverManager;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

/**
 * Servlet implementation class FirstServ
 */
public class FirstServ extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FirstServ() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("empid");
        String name = request.getParameter("ename");
        String age = request.getParameter("age");
        String salary = request.getParameter("salary");
        String design = request.getParameter("design");

        out.println("<html><head><title>Employee Details</title></head><body>");
        out.println("<h1>Employee Details Submitted</h1>");
        out.println("<p><b>ID:</b> " + id + "</p>");
        out.println("<p><b>Name:</b> " + name + "</p>");
        out.println("<p><b>Age:</b> " + age + "</p>");
        out.println("<p><b>Salary:</b> " + salary + "</p>");
        out.println("<p><b>Designation:</b> " + design + "</p>");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Prodapt", "root", "root");

            PreparedStatement stmt = con.prepareStatement(
                "INSERT INTO employee VALUES (?,?,?,?,?)");

            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, age);
            stmt.setString(4, salary);
            stmt.setString(5, design);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                out.println("<p style='color:green;'><b>Record inserted successfully!</b></p>");
            } else {
                out.println("<p style='color:red;'><b>Failed to insert record.</b></p>");
            }

            stmt.close();
            con.close();

        } catch (Exception e) {
            out.println("<p style='color:red;'><b>Error:</b> " + e.getMessage() + "</p>");
            e.printStackTrace();
        }

        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
