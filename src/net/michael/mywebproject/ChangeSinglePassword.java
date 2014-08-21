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
@WebServlet("/ChangeSinglePassword")
public class ChangeSinglePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String username =(String) session.getAttribute("savedUsername");
		String opassword=request.getParameter("OPassword");
		String password=request.getParameter("Password");
		String cpassword=request.getParameter("CPassword");
		session.setAttribute("PrevScreen","main.jsp");

		String ohashpword=null;
 		try {
			ohashpword=SimpleSHA1.SHA1(opassword);
		} catch (NoSuchAlgorithmException e1) {
			out.println("Exception in Hashing of old pword at ChangeSinglePassword class.");
			System.err.println(e1.getMessage());
			e1.printStackTrace();
		}

 		if (cpassword.compareTo(password) != 0) {
			session.setAttribute("PrevScreen","change_singlepass.jsp");
			session.setAttribute("message","User " + username + " confirm password is not the same as new password.");
			request.getRequestDispatcher("message.jsp").forward(request, response);

 		}
 		else {
			Login login = new Login(username,ohashpword);
			try {
				if (login.accountExist()) {
					String hashpword=null;
			 		try {
						hashpword=SimpleSHA1.SHA1(password);
					} catch (NoSuchAlgorithmException e1) {
						out.println("Exception in Hashing routine at ChangeSinglePassword class.");
						System.err.println(e1.getMessage());
						e1.printStackTrace();
					}
					
					if (login.changePassword(username, hashpword)) {
						session.setAttribute("message","User " + username + " password was changed!");
						session.setAttribute("savedHashpword", hashpword);
						request.getRequestDispatcher("message.jsp").forward(request, response);
					}
					else {
						session.setAttribute("message","User " + username + " password change was NOT successful!");
						request.getRequestDispatcher("message.jsp").forward(request, response);
					}
				}
				else {
					out.println("User " + username + ": username or old password is incorrect!");	
					session.setAttribute("message","User " + username + ": Old password is incorrect!");
					request.getRequestDispatcher("message.jsp").forward(request, response);
				}
			} catch (Exception e) {
				 System.out.println("Change Password not successful ");
				 System.err.println(e.getMessage());
				 e.printStackTrace();
	
			}
 		} //else 	
 	} // doPost method
} // ChangeSinglePassword class
