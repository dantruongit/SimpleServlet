package api;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import common.Security;
import service.UserService;

@WebServlet(name ="LoginApi" , urlPatterns = {"/loginApi"})
public class LoginApi extends HttpServlet{
	UserService loginService = new UserService();
	Security security = new Security();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println(username + " " + password);
		boolean isLegit = loginService.checkLogin(username, password);
		if(isLegit) {
			String token = security.getJWTToken(username);
			resp.addHeader("Authenciation", token);
		}
		else {
			resp.sendError(403);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String token = req.getHeader("Authenciation");
		if(token != null) {
			String username = security.decodedJWTToken(token);
			if(username != null) {
				System.out.println("Login Accepted.");
			}
			else {
				System.out.println("Login Rejected.");
			}
		}
	}
}
