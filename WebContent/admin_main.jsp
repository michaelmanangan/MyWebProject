<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dialog Resource System</title>
</head>
<body bgcolor="#FFCC99">
<p ALIGN="right">
<%= new java.util.Date() %>
</p>
<p align="right"><a href="logout.jsp">Logout</a></p>
<% 
	String savedUsername = null;
	String user=(String) session.getAttribute("savedUsername");
	if (user == null) {
		session.setAttribute("message","No user is logged in!"); 
		session.setAttribute("PrevScreen", "index.jsp"); 
		response.sendRedirect("message.jsp");
	} else {
		savedUsername = (String) session.getAttribute("savedUsername");
		session.setAttribute("PrevScreen","admin_main.jsp");
	}
%>	
User Name: <%=savedUsername %>
    <h1 align="center">Dialog Administrator Main Page</h1>
<p ALIGN="center"></p>

	<table width="608" border="2" align="center" cellpadding="3" cellspacing="4">

	<tr>
		<td><div align="center"><a href="view_all_users.jsp">View All Users</a></div></td>
		<td><div align="center"><a href="change_pass.jsp">Change Password</a></div></td>
	</tr>
	<tr>
		<td><div align="center"><a href="edit_all_prof.jsp">Edit All Profiles</a></div></td>
		<td><div align="center"><a href="delete_user.jsp">Delete User</a></div></td>
	</tr>
	</table>
</body>
</html>