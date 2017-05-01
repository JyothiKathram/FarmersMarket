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
 * Servlet implementation class customer_validate
 */
public class customer_validate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public customer_validate() {
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
		
		String Select_crop=" ";
		String[] type = request.getParameterValues("Selectcrop");
		for(int i=0; i<type.length; i++){
			Select_crop+=type[i]+" ";
		}		
		  
		String Update_qty = request.getParameter("Selectqty"); 		
		
		boolean flag_success1 = true;
		

		try
		{
			Class.forName(driver); 
			conn = DriverManager.getConnection(url+dbName,"root", "root");
			
			String sql2 = "select * from farmer_crop where croptype" +" LIKE "+"'"+Select_crop+"'"+";";
			PreparedStatement pst = (PreparedStatement)conn.prepareStatement(sql2);
			// pst.setString(1, Select_crop);
			
			  ResultSet result = pst.executeQuery();
	            out.println("<table>");
	        	out.println("<tr>");
	        	out.println("<td><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; username</b></td>");
	        	out.println("<td><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; emailid</b></td>");
	        	out.println("<td><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; croptype</b></td>");
	        	out.println("<td><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; quantity</b></td>");
	        	out.println("<td><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; price</b></td>");
	        	out.println("</tr>");
	        	out.println("</table>");
	            while(result.next()){
	                
	                String user_name = result.getString("username");
	                String email_id = result.getString("emailid");  
	                String crop_type = result.getString("croptype");
	                String qty = result.getString("quantity");    
	                String prce = result.getString("price");
	                   
	               
	                out.println("<table>");
	            	out.println("<tr>");
	            	out.println("<td><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ user_name+"</td>");
		        	out.println("<td><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ email_id+"</td>");
		        	out.println("<td><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ crop_type+"</td>");
		        	out.println("<td><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ qty+"</td>");
		        	out.println("<td><b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+ prce+"</td>");
		        	out.println("</tr>");
	            	out.println("</table>");
	                out.println("\n");
	            }
	            if(result.wasNull()){
	            	request.setAttribute("errMsg3", "No farmer is providing that crop now");
					
			RequestDispatcher rd = request.getRequestDispatcher("/Customer_functions.jsp");
			rd.forward(request, response); 
	            }
			
		}catch(Exception e){
			System.out.println("DB related Error");
			e.printStackTrace();
		}
		
			}

}
