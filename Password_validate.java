package farmersmarket;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Password_validate
 */
public class Password_validate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Password_validate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter(); 
         response.setContentType("text/html");
 
		    String url="jdbc:mysql://localhost:3306/";
	        String dbName="test";
	        String driver="com.mysql.jdbc.Driver";
	        Connection conn = null;
	        
	        String username = request.getParameter("uname");
	        String pwd1 = request.getParameter("Password1");
	        String pwd2 = request.getParameter("Password2");
	        
	        if(pwd1.equals(pwd2)){	        
	        try
	        {
	        	Class.forName(driver); 
	            conn = DriverManager.getConnection(url+dbName,"root", "root");
	            PreparedStatement statement = (PreparedStatement)conn.prepareStatement("select username from usertable where username=?");
	            statement.setString(1, username);
	         
	            ResultSet result = statement.executeQuery();
	            
	            if(result.next()){
	            	 PreparedStatement statement1 = (PreparedStatement)conn.prepareStatement("UPDATE usertable SET password=? WHERE username=?");
	            	 statement1.setString(1, pwd1);
	            	// statement.setString(2, pwd2);
	            	 statement1.setString(2, username);
	            	 int result1 = statement1.executeUpdate();
	            	 response.sendRedirect("index.jsp");
	            }
	            else{
	            	
	            	request.setAttribute("errMsg5", "Your username is not valid");
					// The following will keep you in the login page
					RequestDispatcher rd = request.getRequestDispatcher("forgot_password.jsp");
					rd.forward(request, response);	
	                
	            }
	        }
	        catch(Exception e){
	            System.out.println("DB related Error");
	            e.printStackTrace();
	        }  
	        }
	        else{
	        	request.setAttribute("errMsg6", "password and confirm should be same");
				// The following will keep you in the login page
				RequestDispatcher rd = request.getRequestDispatcher("forgot_password.jsp");
				rd.forward(request, response);	
	        }
	}


}
