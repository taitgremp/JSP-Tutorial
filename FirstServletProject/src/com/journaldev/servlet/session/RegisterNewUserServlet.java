package com.journaldev.servlet.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(
		description = "Registration Servlet", 
		urlPatterns = { "/RegisterNewUserServlet" }
		)

public class RegisterNewUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get request parameters for userID and password
		String user = request.getParameter("newUserName");
		String pwd = request.getParameter("newpwd");
		String confirmPwd = request.getParameter("confirmPwd");
		
		if(pwd.equals(confirmPwd)) {
		
			HttpSession session = request.getSession(); 
			session.setAttribute("user", user); 
			session.setMaxInactiveInterval(5*30); //5 minutes
			Cookie userName = new Cookie("user", user);
			userName.setMaxAge(5*30);
			response.addCookie(userName);
			response.sendRedirect("LoginSuccess.jsp");
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("RegisterUser.jsp");
			PrintWriter out = response.getWriter();
			out.println("<font color=red>Passwords do not match, please try again</font>");
			rd.include(request, response);
		}
	}
	
}