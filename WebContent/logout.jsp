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
	<h1 align="center">Log out</h1>
	<p ALIGN="center"></p>

<%
	session.setAttribute("savedUsername", null);
	session.setAttribute("savedHashpword", null);
	session.setAttribute("savedRole", 0);
	net.michael.mywebproject.Login.con = null;
%>
	
<table border="1" align="center">
	<tr>
		<td width="30%" align="center">You have been logged out!</td>
	</tr>	
	<tr>
		<td align="center" colspan="1"> 
			<a href="index.jsp">OK</a>
		</td>
	</tr>
</table>
</body>
</html>