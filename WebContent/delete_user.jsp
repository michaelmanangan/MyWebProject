<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dialog Resource System</title>
</head>
<body vLink=#6f6c81 link=#486591 bgcolor="#FFCC99">
<p ALIGN="right">
<%= new java.util.Date() %>
</p>
<p align="right"><a href="logout.jsp">Logout</a></p>
<% 
	String savedUsername = null;
	String username=(String) session.getAttribute("savedUsername");
	if (username == null) {
		session.setAttribute("message","No user is logged in!"); 
		session.setAttribute("PrevScreen", "index.jsp"); 
		response.sendRedirect("message.jsp");
	} else {
		savedUsername = (String) session.getAttribute("savedUsername");
	}
%>	
User Name: <%=(String) session.getAttribute("savedUsername") %>
	<h1 align="center">Delete User</h1>
	<p ALIGN="center"></p>
	<form method="get" action="DeleteUser"> 
<table border="2" align="center" cellpadding="7" cellspacing="7">
	<tr>
		<td width="309"><strong>User Name</strong></td>
		<td width="277"><input type="text" name="dUser" id="dUser">
		<input type="submit" value="submit"></td>
	</tr>	
</table>
</form>
</body>
</html>