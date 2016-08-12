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

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		Cookie[] cookies = request.getCookies();
		//find the login cookie
		for(Cookie cookie: cookies) {
			if(cookie.getName().equals("JSESSIONID")) {
				System.out.println("JSESSIONID=" + cookie.getValue());
				break;
			}
		}
		
		//invalidate the session if it exists
		HttpSession session = request.getSession(false);
		System.out.println("User=" + session.getAttribute("user"));
		if(session != null) {
			session.invalidate();
		}
		RequestDispatcher rd = request.getRequestDispatcher("login.html");
		PrintWriter out = response.getWriter();
		out.println("<font color=blue>You have been successfully logged out of the application</font>");
		rd.include(request, response);
	}	
}