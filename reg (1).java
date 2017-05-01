package farmersmarket;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class reg
 */
public class reg extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 public static int id = 00000;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reg() {
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
		// TODO Auto-generated method stub
		 PrintWriter pw = response.getWriter(); 
         response.setContentType("text/html");
         
         String url="jdbc:mysql://localhost:3306/";
         String dbName="test";
         String driver="com.mysql.jdbc.Driver";
         Connection conn = null;
         
        
         ArrayList<String> al = new ArrayList<>();
         
         String Fname = request.getParameter("FirstName");  
         String Lname = request.getParameter("LastName");  
         String Uname = request.getParameter("UserName");  
         String Emailid = request.getParameter("mailid");  
         String Password1 = request.getParameter("Password");  
         String Password2 = request.getParameter("Confirmpassword");
         String memtype=" ";
         String[] type = request.getParameterValues("Membertype");
         for(int i=0; i<type.length; i++){
         memtype+=type[i]+" ";
         }

         
         try{
        	 String sql = "insert into usertable(id,firstname, lastname, username, emailid, password, password2,membertype) values('"+id+"','"+Fname+"','"+Lname+"','"+Uname+"','"+Emailid+"','"+Password1+"','"+Password2+"','"+memtype+"');";
             
        	 id++;
        	 Class.forName(driver);  
        	 conn = DriverManager.getConnection(url+dbName,"root", "root");
        	 PreparedStatement pst = (PreparedStatement)conn.prepareStatement(sql);
        	
        	 pst.execute();
        	 pw.println("Registered Successfully");
              
        	 response.sendRedirect("user_options.jsp");
        	 
         }catch(Exception e){
        	 pw.println(e);
         
         }
	}

}
