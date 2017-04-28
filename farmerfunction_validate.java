package farmersmarket;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class farmerfunction_validate
 */
public class farmerfunction_validate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public farmerfunction_validate() {
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
		
		String Add_crop=" ";
		String[] type = request.getParameterValues("Addcrop");
		for(int i=0; i<type.length; i++){
			Add_crop+=type[i]+" ";
		}		
		  
		String Update_qty = request.getParameter("Updateqty");  
		String Declare_price= request.getParameter("Declareprice");
		
		boolean flag_success = true;
		

		try
		{
			Class.forName(driver); 
			conn = DriverManager.getConnection(url+dbName,"root", "root");
			
			
			String sql2 = "insert into farmer_crop(croptype, quantity, price) values('"+Add_crop+"','"+Update_qty+"','"+Declare_price+"');";
			PreparedStatement pst = (PreparedStatement)conn.prepareStatement(sql2);
			pst.execute();

			request.setAttribute("errMsg_f1", "Crop details succesfully added to the database");
			RequestDispatcher rd = request.getRequestDispatcher("/Farmer_functions.jsp");
			rd.forward(request, response); 
		//	response.sendRedirect("Farmer_functions.jsp");
			
		}catch(Exception e){
			System.out.println("DB related Error");
			e.printStackTrace();
		}
		
		
	
	}

}
