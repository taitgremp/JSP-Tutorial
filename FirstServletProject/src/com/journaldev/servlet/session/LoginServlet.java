package com.journaldev.servlet.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(
		description = "Login Servlet", 
		urlPatterns = { "/LoginServlet" }, 
		initParams = { 
				@WebInitParam(name = "sqlUser", value = "taitgr"), 
				@WebInitParam(name = "sqlPwd", value = "Jasmine1")
		})

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//get request parameters for userID and password
		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
		
		//get servlet config init params
		String sqlUser = getServletConfig().getInitParameter("sqlUser");
		String sqlPwd = getServletConfig().getInitParameter("sqlPwd");
		
		String userID = "Gabe";
		String password = "journaldev";
		//logging example
		log("User="+user+"::password="+pwd);
//		doDBStuff();
		if(userID.equals(user) && password.equals(pwd)){
			HttpSession session = request.getSession(); 
			session.setAttribute("user", userID); 
			session.setMaxInactiveInterval(5*30);
			Cookie userName = new Cookie("user", user);
			userName.setMaxAge(5*30);
			response.addCookie(userName);
			response.sendRedirect("LoginSuccess.jsp");
		}else{
			response.sendRedirect("failedLogin.html");
			
		}		
	}
	
	private void doDBStuff() {
		try{
	      // This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      connect = DriverManager
	          .getConnection("jdbc:mysql://localhost/feedback?"
	              + "user=sqluser&password=sqluserpw");
		}catch(Exception e) {
			//swallow
		}finally {
			close();
		}
	
	}
	
	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			
			if (statement != null) {
				statement.close();
			}
			
			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
		
		}
	}
}