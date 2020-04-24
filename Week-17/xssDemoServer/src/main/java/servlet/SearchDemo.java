package servlet;

import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
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

@WebServlet(name = "SearchDemo", urlPatterns = {"/SearchDemo"})
public class SearchDemo extends HttpServlet {

    private static Map<String, String> dataToSearch = null;

    public SearchDemo() {
        //Setup af few test items to search for
        if (dataToSearch == null) {
            dataToSearch = new HashMap();
            dataToSearch.put("girls", "They have all the fun ;-)");
            dataToSearch.put("xss", "One of the OWASP top 10 threats");
            dataToSearch.put("security", "Take a look at the code for this server to see how NOT TO DO IT!");
        }
    }

    private void makePageForAuthenticatedUsers(final PrintWriter out, HttpServletRequest request) {
        String search = (String) request.getParameter("searchterm");
        out.println("<h3>Search for great answers on this site!</h3>");
        out.println("<p>Hi "+request.getSession().getAttribute("user")+", your secret is: <span class='data'>" + request.getSession().getAttribute("secret") + "</span></p>");
        out.println("<form method='GET'><input name='searchterm' placeholder='Enter your search term'/><button>Search</button></form>");
        if (search != null) {
            out.println("<br/> The result of your search for the term: <span class='data'>" + search + "</span>");
            String res = dataToSearch.get(search);
            out.println("<div class='results'");
            if (res == null) {
                out.println("<p style='color:red'>No match found in our great database</p>");
            } else {
                out.println("<p class='data'>" + res + "</p>");
            }
            out.println("</div>");
        }
        out.println("<br/><a href='index.html'>Back</a>");
    }

    private void makeLoginForm(HttpServletRequest request, final PrintWriter out) {
        String err = request.getParameter("err");
        if (err != null) {
            out.print("<p style='color:red'>" + err + "</p>");
        }
        out.println("<fieldset><legend>Please login to use our cool site</legend>");
        out.println("<form action='SearchDemo' method='POST'>");
        out.println("<input name='user' placeholder='User name'/></br>");
        out.println("<input name='password' placeholder='Password'/></br>");
        out.println("<br/><input type='submit' />");
        out.println("</form>");
        out.println("</legend>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Search Demo</title>");
            out.println("<link rel='stylesheet' href='styles.css'>");
            out.println("</head>");
            out.println("<body>");
            boolean loggedIn = request.getSession().getAttribute("user") != null;
            if (!loggedIn) {
                makeLoginForm(request, out);
            }
            if (loggedIn) {
                makePageForAuthenticatedUsers(out, request);
            }
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    //Post only used to handle login requests
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
            response.sendRedirect("SearchDemo");
        } catch (Exception ex) {
            response.sendRedirect("SearchDemo?err=" + "Wrong user name or password - please try again");
            Logger.getLogger(StoredXssDemo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            em.close();
        }
    }

}
