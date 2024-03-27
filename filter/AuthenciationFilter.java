package filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Security;

public class AuthenciationFilter implements Filter{
	Security security = new Security();
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
		String ip = request.getRemoteAddr();
		System.out.println("IP: " + ip + " - Logging Time: " + new Date().toString());
		String username = ((HttpServletRequest)request).getParameter("username");
		String token = ((HttpServletRequest)request).getHeader("Authenciation");
		System.out.println("Username: " + username + " Token: " + token);
		if( token != null) {
			String usernameInToken = security.decodedJWTToken(token);
			if(!usernameInToken.equals(username) || usernameInToken == null) {
				((HttpServletResponse) response).sendError(403);
			}
			else {
				chain.doFilter(request, response);
			}
		}
		else {
			((HttpServletResponse) response).sendError(403);
		}
	}
	
}
