<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CheckoutPage</title>
</head>
<body>
<%

//allow access only if session exists

if(session.getAttribute("user") == null) {
	response.sendRedirect("login.html");
}

String userName = null;
String sessionID = null;

Cookie[] cookies = request.getCookies();
for(Cookie cookie: cookies) {
	if(cookie.getName().equals("user")) {
		userName = cookie.getValue();
	}
}
%>
<h3>Welcome <%=userName%>, click below to end session</h3>
<br>
<form action="LogoutServlet" method="post">
<input type="submit" value="Logout" >
</form>
</body>
</html>