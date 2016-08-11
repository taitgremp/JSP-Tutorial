package com.journaldev.servlet;

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
		
		response.setContentType("test/html");
		Cookie loginCookie = null;
		Cookie[] cookies = request.getCookies();
		//find the login cookie
		for(Cookie cookie: cookies) {
			if(cookie.getName().equals("user")) {
				loginCookie = cookie;
				break;
			}
		}
		
		if(loginCookie != null) {
			loginCookie.setMaxAge(0);
        	response.addCookie(loginCookie);
		}
		response.sendRedirect("login.html");
	}	
}