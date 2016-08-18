<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>New User Registration</title>
</head>
<body>

<h3>Register new account below.</h3>
<BR>
<form action="RegisterNewUserServlet" method="post">
	<label>User Name:</label>
	<input type="text" name="newUserName"></input>
	<BR>
	<label>Password:</label>
	<input type="password" name="newpwd"></input>
	<BR>
	<label>Confirm Password:</label>
	<input type="password" name="confirmPwd"></input>
	<BR>
	<input type="submit" value="Submit"></input>
</form>
<BR>
<a href="login.html">Back to login</a>
</body>
</html>