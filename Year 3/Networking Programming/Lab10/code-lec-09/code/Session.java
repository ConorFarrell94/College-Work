import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class Session extends HttpServlet {
     public void doGet(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         HttpSession session = request.getSession(true);
         // print session info
         Date created = new Date(session.getCreationTime());
         Date accessed = new Date(session.getLastAccessedTime());
         out.println("Session ID " + session.getId());
         out.println("Session Created: " + created);
         out.println("Session Last Accessed: " + accessed);
    }   }