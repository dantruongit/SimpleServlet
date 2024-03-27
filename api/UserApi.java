package api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import common.Security;
import model.User;
import payload.ResponseData;
import service.UserService;

@WebServlet(name ="UserApi" , urlPatterns = {"/api/user/get"})
public class UserApi extends HttpServlet{
	UserService userService = new UserService();
	Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		User user = userService.getUserByUsername(username);
		ResponseData response = new ResponseData();
		response.setData(user);
		response.setSuccess(user != null);
		String jsonResp = gson.toJson(response);
		PrintWriter out = new PrintWriter(resp.getOutputStream());
		out.println(jsonResp);
		out.flush();
	}
}
