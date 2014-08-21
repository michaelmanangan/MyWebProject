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
	String username=(String) session.getAttribute("savedUsername");
	if (username == null) {
		session.setAttribute("message","No user is logged in!"); 
		session.setAttribute("PrevScreen", "index.jsp"); 
		response.sendRedirect("message.jsp");
	} else {
		session.setAttribute("PrevScreen", "main.jsp"); 
	}
%>	
User Name: <%=(String) session.getAttribute("savedUsername") %>
<h1 align="center">Dialog User Main Page</h1>
	<form method="get" action="check.jsp">
	<table width="608" border="2" align="center" cellpadding="1" cellspacing="3">

	<tr>
		<td><div align="center"><a href="ViewUser">View Profile</a></div></td>
		<td><div align="center"><a href="change_singlepass.jsp">Change Password</a></div></td>
	</tr>
	<tr>
		<td><div align="center"><a href="change_singleprof.jsp">Edit Profile</a></div></td>
	</tr>
	</table>
	</form>

</body>
</html>