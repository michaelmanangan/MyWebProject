package net.michael.mywebproject;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewAllUsers
 */
@WebServlet("/ViewAllUsers")
public class ViewAllUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user=request.getParameter("vuser");
		String username=request.getParameter("Username");
		String password=request.getParameter("Password");
		//String redirectURL=null;	
		
		Login login = new AdminLogin(username,password);
		AdminLogin admin =(AdminLogin) login;

		// display all users
		try {
			ResultSet rs=null;
			if ((user == null) || (user.compareTo("*")  == 0) || (user.compareTo("") == 0)) {
				rs=admin.displayAllUsers();
			}
			else {
				rs=login.displayUser(user);
			}
			HttpSession session = request.getSession();
			session.setAttribute("Resultlist", rs );
			request.getRequestDispatcher("display_users.jsp").forward(request, response);

		} catch (Exception e) {
			 System.out.println("ViewAllUsers servlet at admin.displayAllUsers exception!");
			 System.err.println(e.getMessage());
			 e.printStackTrace();
		}
			
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
