package net.michael.mywebproject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangeSingleProf")
public class ChangeSingleProf extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int newRole=Integer.parseInt(request.getParameter("radio1"));
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("savedUsername");
		String password=(String) session.getAttribute("savedPassword");
		int currentRole=(int) session.getAttribute("savedRole");
		
		
		Login login = new Login(username,password);
		try {
			//at this point, it is already assumed that the username already exists
			if (currentRole == newRole){
				session.setAttribute("message", "User " + username + " current and new role are already the same!");
			}
			else {
				if (login.changeProf(username, newRole)) {
					session.setAttribute("message", "User " + username + " role was changed!" );
					session.setAttribute("savedRole", newRole);
				}
				else {
					session.setAttribute("message", "User " + username + " does not exist. Role change is NOT successful!");
				}
			}
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} catch (Exception e) {
			 System.out.println("Change Password not successful ");
			 System.err.println(e.getMessage());
			 e.printStackTrace();
		}
	}
}
