import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Parameter extends HttpServlet {
     public void doGet(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         out.println("<html>");
         out.println("<body>");
         out.println("<h1>  Hello \t" + request.getParameter("firstname"));
		 out.println( "Welcome to Servlets! </h1>" );
         out.println("</body>");
         out.println("</html>");    
    }   }