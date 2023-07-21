import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Cookies extends HttpServlet {
     public void doPost(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
		 String language = request.getParameter( "language" );
	     Cookie cookie = new Cookie( "Book", language );
		 cookie.setMaxAge(60*60); 
		 response.addCookie( cookie ); 
         out.println("<html>");
         out.println("<body>");
         out.println( "<h1> Cookie is created  with name Book and value is ");
		 out.println(language+"</h1>" );
         out.println("</body>");
         out.println("</html>");    
    }   }