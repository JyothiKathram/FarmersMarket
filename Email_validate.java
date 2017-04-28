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
 * Servlet implementation class Email_validate
 */
public class Email_validate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Email_validate() {
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
	        
	        String mailid = request.getParameter("email");
	        
	        try
	        {
	        	Class.forName(driver); 
	            conn = DriverManager.getConnection(url+dbName,"root", "root");
	            PreparedStatement statement = (PreparedStatement)conn.prepareStatement("select username,password,emailid from usertable where emailid=?");
	            statement.setString(1, mailid);
	         
	            ResultSet result = statement.executeQuery();
	            
	            if(result.next()){
	            	String username = result.getString("username");
	            	 out.println("Your username is "+ username);	            	
	            }
	            else{
	            	
	            	request.setAttribute("errMsg4", "Your emailid is not registered in the database");
					// The following will keep you in the login page
					RequestDispatcher rd = request.getRequestDispatcher("forgot_username.jsp");
					rd.forward(request, response);	
	                
	            }
	        }catch(Exception e){
	            System.out.println("DB related Error");
	            e.printStackTrace();
	        }   
	}

}
