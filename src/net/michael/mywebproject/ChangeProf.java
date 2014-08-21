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
@WebServlet("/ChangeProf")
public class ChangeProf extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int newRole=Integer.parseInt(request.getParameter("radio1"));
		String username=request.getParameter("editUser");
		HttpSession session=request.getSession();
		
		Login login = new Login(username,"password");
		try {
			if (login.changeProf(username, newRole)) {
				session.setAttribute("message","User " + username + " role was changed!" );
				String savedUsername = (String) session.getAttribute("savedUsername");
				if (savedUsername.compareTo(username) == 0 ) {
					session.setAttribute("savedRole", newRole);
				}
			}
			else {
				session.setAttribute("message","User " + username + " does not exist. Role change is NOT successful!");
			}
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} catch (Exception e) {
			 System.out.println("Edit all profile not successful ");
			 System.err.println(e.getMessage());
			 e.printStackTrace();
		}
	}

}
