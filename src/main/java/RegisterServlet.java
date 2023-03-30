import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.*;

@MultipartConfig
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    Connection con = Connectivity.Create();
    PreparedStatement ps;
    Statement st;
    ResultSet rs;

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        PrintWriter out = resp.getWriter();
//        resp.setContentType("text/html");
//
//        String first = req.getParameter("first");
//        String last = req.getParameter("last");
//        String dob = req.getParameter("dob");
//        String email = req.getParameter("email");
//        String pwd = req.getParameter("pwd");
//        String address = req.getParameter("address");
//        String phone = req.getParameter("phone");
//        String image = req.getParameter("img");
//
//
//            Part file = req.getPart("img");
//            String imageFileName = file.getSubmittedFileName();
//            String uploadPath = "C:/Users/oditas/IdeaProjects/RegistrationForm/src/main/webapp/Images/"+imageFileName;
//
//        FileOutputStream fos = new FileOutputStream(uploadPath);
//        InputStream is = file.getInputStream();
//
//        byte[] data = new byte[is.available()];
//        is.read(data);
//        fos.write(data);
//        fos.close();
//
//
//        try {
//            ps = con.prepareStatement("insert into register values(?,?,?,?,?,?,?,?)");
//            ps.setString(1,first);
//            ps.setString(2,last);
//            ps.setString(3,dob);
//            ps.setString(4,email);
//            ps.setString(5,pwd);
//            ps.setString(6,address);
//            ps.setString(7,phone);
//            ps.setString(8,imageFileName);
//
//            ps.execute();
//
//            out.println("<h2 style='color:green'>Registered Sucessfully!</h2><br>");
//            out.println("<a href='login.html'>Login</a>");
//            RequestDispatcher rd = req.getRequestDispatcher("index.html");
//            rd.include(req,resp);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        String first = req.getParameter("first");
        String last = req.getParameter("last");
        String dob = req.getParameter("dob");
        String email = req.getParameter("email");
        String pwd = req.getParameter("pwd");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String image = req.getParameter("img");


        Part file = req.getPart("img");
        String imageFileName = file.getSubmittedFileName();
        String uploadPath = "C:/Users/oditas/IdeaProjects/RegistrationForm/src/main/webapp/Images/"+imageFileName;

        FileOutputStream fos = new FileOutputStream(uploadPath);
        InputStream is = file.getInputStream();

        byte[] data = new byte[is.available()];
        is.read(data);
        fos.write(data);
        fos.close();


        try {
            ps = con.prepareStatement("insert into register values(?,?,?,?,?,?,?,?)");
            ps.setString(1,first);
            ps.setString(2,last);
            ps.setString(3,dob);
            ps.setString(4,email);
            ps.setString(5,pwd);
            ps.setString(6,address);
            ps.setString(7,phone);
            ps.setString(8,imageFileName);

            ps.execute();

            out.println("<div align='center'><h2 style='color:green'>Registered Sucessfully!</h2><br>");
            out.println("<h2><a href='login.html'>Login</a></h2><br></div>");
            RequestDispatcher rd = req.getRequestDispatcher("index.html");
            rd.include(req,resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
