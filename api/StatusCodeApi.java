package api;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="TestApi", urlPatterns = {"/api/statuscode/*"})
public class StatusCodeApi extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		System.out.println(path);
		resp.setStatus(404);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(),"UTF-8"),true);
		out.println(path);
	}
}
