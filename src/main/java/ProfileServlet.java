import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {

    Connection con = Connectivity.Create();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        if(session.getAttribute("name")==null){
            resp.sendRedirect("login.html");
        }

        else {
            PrintWriter out = resp.getWriter();
            resp.setContentType("text/html");


            String name = (String) session.getAttribute("name");
            String email = (String) session.getAttribute("email");

            try {
                ps = con.prepareStatement("select * from register where firstname=? and email=?");
                ps.setString(1, name);
                ps.setString(2,email);
                rs = ps.executeQuery();

                while (rs.next()) {
                    String first = rs.getString(1);
                    String last = rs.getString(2);
                    String dob = rs.getString(3);
                    String email2 = rs.getString(4);
                    String address = rs.getString(6);
                    String phone = rs.getString(7);
                    String image = rs.getString(8);

                    out.println("<div align='center'><img src='Images/"+image+"' alt='Profile Image' width='200' height='200'>");
                    out.println("<h2>Name:" + first + last + "</h2>");
                    out.println("<h2>DOB:" + dob + "</h2>");
                    out.println("<h2>Email:" + email2 + "</h2>");
                    out.println("<h2>Address:" + address + "</h2>");
                    out.println("<h2>Phone:" + phone + "</h2></div>");

                }

                out.println("<div align='center'><h2><a href='WelcomeServlet'>Back</a></h2></div>");


            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
