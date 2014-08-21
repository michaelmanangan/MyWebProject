package net.michael.mywebproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String username=request.getParameter("Username");
		String password=request.getParameter("Password");
		String cpassword=request.getParameter("CPassword");
		HttpSession session=request.getSession();
		session.setAttribute("PrevScreen","admin_main.jsp");
		String hashpword=null;
 		try {
			hashpword=SimpleSHA1.SHA1(password);
		} catch (NoSuchAlgorithmException e1) {
			out.println("Exception in Hashing routine at ChangePassword class.");
			System.err.println(e1.getMessage());
			e1.printStackTrace();
		}
		if (cpassword.compareTo(password) != 0) {
		//	System.out.println("Confirm password is not correct");
		//	response.sendRedirect(redirectURL);
			session.setAttribute("PrevScreen","change_pass.jsp");
			session.setAttribute("message","User " + username + " confirm password is not the same as new password.");
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} else {
	 		Login login = new AdminLogin(username, "dummypword");
			if (login.changePassword(username, hashpword)) {
				session.setAttribute("message","User " + username + " password was changed!");
				String savedUsername = (String) session.getAttribute("savedUsername");
				if (savedUsername.compareTo(username) == 0) {
					session.setAttribute("savedHashpword", hashpword);
				}
				request.getRequestDispatcher("message.jsp").forward(request, response);
	
			}
			else {
				session.setAttribute("message","User " + username + " password change failed as user does not exist!");
				request.getRequestDispatcher("message.jsp").forward(request, response);
			}
		}
	}

}
