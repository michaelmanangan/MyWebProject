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
 * Servlet implementation class MyServlet
 */
@WebServlet("/MainLogin")
public class MainLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		PrintWriter out = response.getWriter();
//		String userName = request.getParameter("userName");
//		String passWord = request.getParameter("pass");
		
//		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("spring.xml"));
//		MyModel model = (MyModel) factory.getBean("myusers");

//		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml"); 
//		MyModel model = (MyModel) context.getBean("myusers");
		
//		model.addRec(userName, passWord);
//		model.showRecs(out);

		response.setContentType("text/html");
		HttpSession session = request.getSession();
		session.setAttribute("PrevScreen","index.jsp");
		
		String username=request.getParameter("Username");
		String password=request.getParameter("Password");
		
		String hashpword=null;
 		try {
			hashpword=SimpleSHA1.SHA1(password);
		} catch (NoSuchAlgorithmException e1) {
			session.setAttribute("message","Exception in Hashing routine at MainLogin class.") ;
			System.err.println(e1.getMessage());
			e1.printStackTrace();
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
		
		request.setAttribute("Username", username);
		request.setAttribute("Hashpword", hashpword );
		
		try
		{
			Login login = new Login(username,hashpword);
			if (login.accountExist()) {
				request.setAttribute("Role", login.getRole());
				String redirectURL = null;
				session.setAttribute("savedUsername", username);
				session.setAttribute("savedHashpword", hashpword);
				session.setAttribute("savedRole", login.getRole());
				if (login.getRole() == 1) {
				  redirectURL = "admin_main.jsp";	
				} else {
				  redirectURL = "main.jsp";
				}
				request.getRequestDispatcher(redirectURL).forward(request, response);
			}
			else {
				session.setAttribute("message","No account or password mismatch.") ;
				request.getRequestDispatcher("message.jsp").forward(request, response);
			}
				
		} catch (Exception e) {
			session.setAttribute("message","Exception in MainLogin class.") ;
			request.getRequestDispatcher("message.jsp").forward(request, response);
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
