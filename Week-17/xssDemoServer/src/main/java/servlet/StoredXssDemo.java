package servlet;

import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.security.sasl.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import settings.Settings;

@WebServlet(name = "StoredXssDemo", urlPatterns = {"/StoredXssDemo"})
public class StoredXssDemo extends HttpServlet {

    private void makePageForAuthenticatedUsers(final PrintWriter out, HttpServletRequest request) {
        out.println("<h1>Welcome: <span class='data'>" + request.getSession().getAttribute("user") + "</span></h1>");
        out.println("<h4>You are logged in as: <span class='data'>" + request.getSession().getAttribute("role") + "</span></h4>");
        out.println("<h4>You secret is: <span class='data'>" + request.getSession().getAttribute("secret") + "</span></h4>");
        EntityManager em = Persistence.createEntityManagerFactory(Settings.getPuName()).createEntityManager();
        try {
            List<User> users = em.createNamedQuery("User.findAll").getResultList();
            out.println("<ul>");
            for (User user : users) {
                out.println("<li>" + user.getUsername() + "</li>");
            }
            out.println("</ul>");
            out.println("<br/><a href='index.html'>Back</a>");
            
        } finally {
            em.close();
        }
    }

    private void makeLoginForm(HttpServletRequest request, final PrintWriter out) {
        String err = request.getParameter("err");
        if (err != null) {
            out.print("<p style='color:red'>" + err + "</p>");
        }
        out.println("<fieldset><legend>Please login to use our cool site</legend>");
        out.println("<form action='StoredXssDemo' method='POST'>");
        out.println("<input name='user' placeholder='User name'/></br>");
        out.println("<input name='password' placeholder='Password'/></br>");
        out.println("<br/><input type='submit' />");
        out.println("</form>");
        out.println("</legend>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Stored XSS Demo</title>");
            out.println("<link rel='stylesheet' href='styles.css'>");
            out.println("</head>");
            out.println("<body>");

            boolean loggedIn = request.getSession().getAttribute("user") != null;
            if (!loggedIn) {
                makeLoginForm(request, out);
            } else {
                makePageForAuthenticatedUsers(out, request);
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = Persistence.createEntityManagerFactory(Settings.getPuName()).createEntityManager();
        try {
            String userName = (String) request.getParameter("user");
            String pw = (String) request.getParameter("password");
            User user = em.find(User.class, userName);
            if (user == null || !user.getPassword().equals(pw)) {
                throw new AuthenticationException();
            }
            request.getSession().setAttribute("user", userName);
            request.getSession().setAttribute("role", user.getRole());
            request.getSession().setAttribute("secret", user.getSecret());
            response.sendRedirect("StoredXssDemo");

        } catch (Exception ex) {
            response.sendRedirect("StoredXssDemo?err=" + "Wrong user name or password - please try again");
            Logger.getLogger(StoredXssDemo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }

}
