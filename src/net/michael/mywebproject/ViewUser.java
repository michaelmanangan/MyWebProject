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
 * Servlet implementation class ViewUser
 */
@WebServlet("/ViewUser")
public class ViewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("savedUsername");
		String hashpword=(String) session.getAttribute("savedHashpword");
		Login login = new Login(username,hashpword);

		// display a single user
		try {
			if (login.accountExist()) {
				ResultSet rs=login.displayUser(username);
				session.setAttribute("Resultlist", rs );
				request.getRequestDispatcher("display_users.jsp").forward(request, response);
			}
			else {
				session.setAttribute("message","User " + username + " not found!");
				request.getRequestDispatcher("message.jsp").forward(request, response);
			}
				
		} catch (Exception e) {
			 session.setAttribute("message","ViewUser servlet exception!");
			 request.getRequestDispatcher("message.jsp").forward(request, response);
			 
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
