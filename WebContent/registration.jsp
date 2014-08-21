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
<h1 align="center">Dialog Registration Page</h1>
	<form method="post" action="RegistrationCheck">
	<table width="318" border="0" align="center" cellpadding="2" cellspacing="3">
		<tr>
			<td>UserName</td>
			<td><input type="text" name="Username" size="20" /> </td>
		</tr>
		<tr>
			<td>Role</td>
	        <td><input type="radio" name="radio1" value="2" checked /> User <br>
	         <input type="radio" name="radio1" value="1" /> Administrator <br></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" name="Password" size="20" /></td>
		</tr>	
		<tr>
			<td>Confirm Password</td>
			<td><input type="password" name="CPassword" size="20" /></td>
		</tr>
		<tr>
			<td align="right" colspan="2">	
				<input name="s" type="submit" id="s" value="Register" />
				<input name="reset" type="reset" id="s" value="Reset" /></td>
		</tr>
	</table>
	</form>
<% session.setAttribute("PrevScreen","index.jsp"); %>
</body>
</html>