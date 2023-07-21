import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
public class Redirect extends HttpServlet {    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException {
         String location = request.getParameter( "page" );
         if ( location != null )
			 if ( location.equals( "dit" ) )
				 response.sendRedirect( "http://www.dit.ie" );
			 else
                 if ( location.equals( "HelloWorld" ) )
                     response.sendRedirect( "HelloWorld" );
    }   }