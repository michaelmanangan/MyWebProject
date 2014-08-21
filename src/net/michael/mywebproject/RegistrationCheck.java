package net.michael.mywebproject;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RegistrationCheck
 */
@WebServlet("/RegistrationCheck")
public class RegistrationCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("Username");
		int role=Integer.parseInt(request.getParameter("radio1"));
		String password=request.getParameter("Password");
		String cpassword=request.getParameter("CPassword");
		HttpSession session=request.getSession();
		session.setAttribute("PrevScreen","index.jsp");
		String hashpword=null;
 		try {
			hashpword=SimpleSHA1.SHA1(password);
		} catch (NoSuchAlgorithmException e1) {
			System.out.println("Exception in Hashing routine at MainLogin class.");
			System.err.println(e1.getMessage());
			e1.printStackTrace();
		}

		Login login = new Login(username,hashpword);
		Login newUser = null;
		
		try
		{   
			if (cpassword.compareTo(password) != 0) {
				session.setAttribute("PrevScreen","registration.jsp");
				session.setAttribute("message","Password is not same as confirmation password.");
				request.getRequestDispatcher("message.jsp").forward(request, response);
			} 
			else {
				newUser = login.addAccount(role); 
				if (newUser == null) {
					session.setAttribute("PrevScreen","registration.jsp");
					session.setAttribute("message","Account already exists.") ;
					request.getRequestDispatcher("message.jsp").forward(request, response);
				}
				else {
					if (newUser instanceof AdminLogin) {
						session.setAttribute("message","Admin Account inserted.");
						request.getRequestDispatcher("message.jsp").forward(request, response);
					}
					else {
						session.setAttribute("message","User Account inserted.");
						request.getRequestDispatcher("message.jsp").forward(request, response);
					}
				}
			}	
		} catch (Exception e) {
			 System.out.println("Account not registered!");
			 System.err.println(e.getMessage());
			 e.printStackTrace();
		}
	}
}
