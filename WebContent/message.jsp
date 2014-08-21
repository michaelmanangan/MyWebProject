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
	<h1 align="center">Message</h1>
	<p ALIGN="center"></p>
<table border="2" align="center">
	<tr>
		<td width="30%" align="center"><%=(String) session.getAttribute("message") %></td>
	</tr>	
	<tr>
		<td align="center" colspan="1"> 
			<a href='<%=(String) session.getAttribute("PrevScreen")%>'>OK</a>
		</td>
	</tr>
</table>
</body>
</html>