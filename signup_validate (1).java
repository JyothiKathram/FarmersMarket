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
 * Servlet implementation class signup_validate
 */
public class signup_validate extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public signup_validate() {
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
		
		String password_checker = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,15})";
	    String email_checker = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	boolean flag_uname = true;
	boolean flag_mail = true;
	boolean flag_passwordnuname = true;
	boolean flag_pwd1npwd2 = true;
	boolean flag_pwd = true;
	boolean flag_emailcheck = true;

		try
		{
			Class.forName(driver); 
			conn = DriverManager.getConnection(url+dbName,"root", "root");
			String sql = "select username from usertable where username =?";
			PreparedStatement statement = (PreparedStatement)conn.prepareStatement(sql);
			statement.setString(1, Uname);
			
			ResultSet result = statement.executeQuery();
			if(result.next()){
				System.out.println(result);
				request.setAttribute("errMsg1", "username already exists, choose a different username");
				// The following will keep you in the login page
				RequestDispatcher rd = request.getRequestDispatcher("/signup.jsp");
				rd.forward(request, response); 
				flag_uname = false;
			}	               
			String sql1 = "select emailid from usertable where  emailid=?";
			PreparedStatement stmt = (PreparedStatement)conn.prepareStatement(sql1);
			stmt.setString(1,Emailid );
			ResultSet result1 = stmt.executeQuery();
			if(result1.next()){
				request.setAttribute("errMsg2", "mailid already exists, please enter a different mailid");
				// The following will keep you in the login page
				RequestDispatcher rd = request.getRequestDispatcher("/signup.jsp");
				rd.forward(request, response); 
				flag_mail= false;
			} 
			
			if(Password1.equals(Uname)){
				request.setAttribute("errMsg11", "password must be different from username");
				// The following will keep you in the login page
				RequestDispatcher rd = request.getRequestDispatcher("/signup.jsp");
				rd.forward(request, response); 
				flag_passwordnuname= false;				
			}
			if(!(Password1.equals(Password2))){
				request.setAttribute("errMsg12", "password and confirmpwd must be the same");
				// The following will keep you in the login page
				RequestDispatcher rd = request.getRequestDispatcher("/signup.jsp");
				rd.forward(request, response); 
			    flag_pwd1npwd2= false;				
			}
			if(!(Password1.matches(password_checker))){
				request.setAttribute("errMsg13", "password must be minimum 6 character and max 10 char long, must have one digit, one uppercase, one lowercase, any special character");
				// The following will keep you in the login page
				RequestDispatcher rd = request.getRequestDispatcher("/signup.jsp");
				rd.forward(request, response); 
			    flag_pwd= false;		
			}
			if(!(Emailid.matches(email_checker))){
				request.setAttribute("errMsg14", "email id must be in the form of example@mail.com ");
				// The following will keep you in the login page
				RequestDispatcher rd = request.getRequestDispatcher("/signup.jsp");
				rd.forward(request, response); 
			    flag_emailcheck= false;		
			} 
			
			 if(flag_uname == true && flag_mail == true && flag_passwordnuname == true && flag_pwd1npwd2== true && flag_pwd== true && flag_emailcheck== true){
				String sql2 = "insert into usertable(firstname, lastname, username, emailid, password, password2,membertype) values('"+Fname+"','"+Lname+"','"+Uname+"','"+Emailid+"','"+Password1+"','"+Password2+"','"+memtype+"');";
		
				PreparedStatement pst = (PreparedStatement)conn.prepareStatement(sql2);
				pst.execute();
				out.println("Registered Successfully");
				response.sendRedirect("index.jsp");
			}


		}catch(Exception e){
			System.out.println("DB related Error");
			e.printStackTrace();
		}   
	}

}
