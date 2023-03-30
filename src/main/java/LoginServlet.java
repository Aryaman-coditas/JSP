
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    Connection con = Connectivity.Create();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");


        String email = req.getParameter("email");
        String pwd = req.getParameter("pwd");
        HttpSession session = req.getSession();
        int count=0;

        try {
            ps = con.prepareStatement("select email,password,firstname from register");
            rs = ps.executeQuery();
            String name="";

            while (rs.next()){
                if(email.equals(rs.getString(1)) && pwd.equals(rs.getString(2))){
                    name = rs.getString(3);
                    count++;
                    break;
                }
            }

            if(count==1){
                session.setAttribute("name",name);
                resp.sendRedirect("WelcomeServlet");
            }
            else {
                out.println("<h2 style='color:red'>Invalid Credentials!</h2><br>");
                RequestDispatcher rd = req.getRequestDispatcher("login.html");
                rd.include(req,resp);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
