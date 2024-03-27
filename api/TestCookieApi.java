package api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "testCookie", urlPatterns = {"/api/testCookie"})
public class TestCookieApi extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie cookie = new Cookie("username", "guest");
		cookie.setMaxAge(60 * 60 * 24); // Thời gian hết hạn của cookie (đơn vị second)
		cookie.setHttpOnly(true); // Set chỉ sử dụng trong http ( tránh bị Javascript truy cập)
		cookie.setSecure(true);
		
		resp.addCookie(cookie);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		for(Cookie cookie: cookies) {
			System.out.println("Cookie: " + cookie.getName() + " - "+ cookie.getValue());
		}
	}
}
