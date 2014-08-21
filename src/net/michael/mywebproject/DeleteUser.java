package net.michael.mywebproject;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DeleteUser
 */
@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dUser=request.getParameter("dUser");
		String username=request.getParameter("Username");
		String password=request.getParameter("Password");
		HttpSession session=request.getSession();
		//String redirectURL=null;	
		
		Login login = new AdminLogin(username,password);
		AdminLogin admin =(AdminLogin) login;

		// delete a single user
		try {
			if (admin.deleteUser(dUser)) {
				session.setAttribute("message","User " + dUser + " was deleted!");
			}
			else {
				session.setAttribute("message","User " + dUser + " not found!");
			}
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} catch (Exception e) {
			 System.out.println("DeleteUser servlet at admin.deleteUser exception!");
			 System.err.println(e.getMessage());
			 e.printStackTrace();
		}
			
	}
		

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
