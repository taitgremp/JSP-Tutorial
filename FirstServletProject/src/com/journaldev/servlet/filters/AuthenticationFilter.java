package com.journaldev.servlet.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

	private ServletContext context;
	private List<String> ignoreURIs = Arrays.asList("html", "LoginServlet", "RegisterUser.jsp", "NewUserServlet");
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response; 
		
		String uri = req.getRequestURI();
		this.context.log("Requested Resource::"+uri);
		
		HttpSession session = req.getSession(false);
		
		if(session == null && !uriAuthorized(uri)) {
			this.context.log("Unauthorized access to uri: " + uri);
			resp.sendRedirect("login.html");
		}else {
			chain.doFilter(request, response);
		}
	}
	
	private boolean uriAuthorized(String uri) {
		boolean ok = false;
		for(String uriEnding: ignoreURIs) {
			if(uri.endsWith(uriEnding)) {
				ok = true;
				break;
			}
		}
		return ok;
	}
	
	@Override
	public void destroy() {
		// not used
		
	}





}
