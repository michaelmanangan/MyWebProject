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
	String savedUsername =(String) session.getAttribute("savedUsername");
	if (savedUsername == null) {
		session.setAttribute("message","No user is logged in!"); 
		session.setAttribute("PrevScreen", "index.jsp"); 
		response.sendRedirect("message.jsp");
	}
%>	
User Name: <%=savedUsername %>
	<h1 align="center">Display Users</h1>
	<p ALIGN="center"></p>
<table border="2" align="center" cellpadding="3" cellspacing="4">
	<tr>
		<td width="30%"><strong>User Name</strong></td>
		<td width="30%"><strong>Role</strong>
		<td width="30%"><strong>Function</strong>
	</tr>	

	<%
		java.sql.ResultSet rs=(java.sql.ResultSet) session.getAttribute("Resultlist");
		String userName = null;
		String viewURL = null;
		String deleteURL=null;
		String changePassURL=null;
		while (rs.next()) {
			userName = rs.getString(1);
			viewURL="ViewAllUsers?vuser=" + userName;
			deleteURL="DeleteUser?dUser=" + userName;
			changePassURL="change_pass.jsp?username=" + userName; 
			%>
		<tr>
		<td><a href="<%=viewURL%>"><%= userName %></a></td>
		<td><%= rs.getString(3) %></td>
		<td><a href="<%=deleteURL%>">Delete User</a> - <a href="<%=changePassURL%>">Change Password</a>
		</td>
		</tr>
	<%	} %>
		<tr>
			<td align="center" colspan="3"> 
			<a href='<%=(String) session.getAttribute("PrevScreen")%>'>Main</a>
		</tr>
</table>
</body>
</html>