package servlet;

import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import settings.Settings;

@WebServlet(name = "addUser", urlPatterns = {"/AddUser"})
public class AddUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Create User</title>");
            out.println("<link rel='stylesheet' href='styles.css'>");
            out.println("</head>");
            out.println("<body>");

            boolean loggedIn = request.getSession().getAttribute("user") != null;
            if (loggedIn) {
                System.out.println("Context"+ request.getServletContext().getContextPath());
                //response.sendRedirect("AddUser?err=You are already logged in as a valid user");
                response.sendRedirect(request.getServletContext().getContextPath());  //No reason to handle "create new user for a user already logged in"
            }
            out.println("<fieldset><legend>Create yourself on our cool site</legend>");
            String err = request.getParameter("err");
            if (err != null) {
                out.print("<p style='color:red'>" + err + "</p>");
            }
            out.println("<form action='AddUser' method='POST'>");
            out.println("<input name='user' class='inp' placeholder='User name'/></br>");
            out.println("<input name='password' class='inp' placeholder='Password'/></br> ");
            out.println("<textarea rows='3' cols='35' name='secret' class='inp' placeholder='Type your secret'></textarea></br>");
            out.println("<br/><input type='submit' />");  
            out.println("</form>");
            if(err != null){
              out.println("<br/><a href='index.html'>Back</a>");
            }
            out.println("</fieldset>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = (String) request.getParameter("user");
        String pw = (String) request.getParameter("password");
        String secret = (String) request.getParameter("secret");
        if (userName == null || userName.equals("") || pw == null || pw.equals("") || secret == null || secret.equals("")) {
            response.sendRedirect("AddUser?err=All fields are required");
        }
        String n = userName;
        String p = pw;
        String s = secret;
        if (Settings.escapeUntrustedData() && !userName.startsWith("hacky")) {
            n = Jsoup.clean(userName, Whitelist.basic());
            p = Jsoup.clean(pw, Whitelist.basic());
            s = Jsoup.clean(secret, Whitelist.basic());
        }
        //System.out.println(n + ", " + p + ", " + s);

        try {
            User user = new User(n, p, s);
            EntityManager em = Persistence.createEntityManagerFactory(Settings.getPuName()).createEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            request.getSession().setAttribute("user", user.getUsername());
            request.getSession().setAttribute("role", user.getRole());
            request.getSession().setAttribute("secret", user.getSecret());
            response.sendRedirect(request.getServletContext().getContextPath());
        } catch (Exception ex) {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("AddUser?err=Error, maybe this user already existed?");
        }
    }
}
