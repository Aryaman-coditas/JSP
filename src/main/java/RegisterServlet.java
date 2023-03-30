import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    Connection con = Connectivity.Create();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        String first = req.getParameter("first");
        String last = req.getParameter("last");
        String dob = req.getParameter("dob");
        String email = req.getParameter("email");
        String pwd = req.getParameter("pwd");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");

        try {
            ps = con.prepareStatement("insert into register values(?,?,?,?,?,?,?)");
            ps.setString(1,first);
            ps.setString(2,last);
            ps.setString(3,dob);
            ps.setString(4,email);
            ps.setString(5,pwd);
            ps.setString(6,address);
            ps.setString(7,phone);
            ps.execute();

            out.println("<h2 style='color:green'>Registered Sucessfully!</h2><br>");
            out.println("<a href='login.html'>Login</a>");
            RequestDispatcher rd = req.getRequestDispatcher("index.html");
            rd.include(req,resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
