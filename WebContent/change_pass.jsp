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
<p align="right"><a href="logout.jsp">Logout</a></p>
User Name: <%=savedUsername %>
<h1 align="center">Change Password</h1>
	<% 
	String nextURL="ChangePassword";
	String username=request.getParameter("username");
	if (username != null) {
		nextURL+="?username=" + username;
	}
	else {
	    username = savedUsername;
	}
	%>
	<form method="post" action=<%=nextURL%>>
	<table width="330" border="2" align="center" cellpadding="1" cellspacing="1">
		<tr>
			<td>User Name</td>
			<td><input type="text" name="Username" size="20" value=<%=username%>  
			   /></td>
		</tr>
		<tr>
			<td>New Password</td>
			<td><input type="password" name="Password" size="20" /></td>
		</tr>	
		<tr>
			<td>Confirm New Password</td>
			<td><input type="password" name="CPassword" size="20" /></td>
		</tr>
		<tr>
			<td align="right" colspan="2">	
				<input name="s" type="submit" id="s" value="Change" />
				<input name="reset" type="reset" id="s" value="Reset" /></td>
		</tr>
	</table>
	</form>

</body>
</html>