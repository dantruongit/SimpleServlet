package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.LoginService;

@WebServlet(name="WebLogin", urlPatterns = "/login")
public class LoginServlet extends HttpServlet{
	private LoginService loginService = new LoginService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("./login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tk = req.getParameter("tk");
		String mk = req.getParameter("mk");
		
		if(loginService.checkLogin(tk, mk)) {
			System.out.println("Login Success");
			req.getRequestDispatcher("./index.html").forward(req, resp);
		}
		else {
			System.out.println("Login failured");
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}
}
