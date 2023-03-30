import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        HttpSession session = req.getSession();

        if(session.getAttribute("name")==null){
            resp.sendRedirect("login.html");
        }

        else {
            String name = (String) session.getAttribute("name");

            out.println("<h1> Welcome "+name+"!</h1><br>");
            out.println("<h2><a href='ProfileServlet'>Profile</a></h2>");
            out.println("<h2><a href='LogoutServlet'>Logout</a></h2>");
        }

    }
}
