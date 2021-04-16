package it.unisa.filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
		
		String loginURI = hrequest.getContextPath() + "/admin";
		boolean loginRequest = hrequest.getRequestURI().startsWith(loginURI);

		if(loginRequest) {
			System.out.println("Check role in the session");
			//check the token from session
			HttpSession session = hrequest.getSession(false);
			boolean loggedIn = (session != null && (privilegedAccess((String) session.getAttribute("role"))));
			if(!loggedIn) {
				hresponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			} else {
				// admin resource
				chain.doFilter(request, response);
			}
		} else {
			// accessible resource
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Init the filter");
	}
	
	private boolean privilegedAccess(String role) {
		if (role != null && !role.equalsIgnoreCase("user"))
			return true;
		else
			return false;
	}

}