package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import service.ProductService;

@WebServlet(name = "productServlet", urlPatterns = {"/products"})
public class ProductServlet extends HttpServlet{
	private ProductService productService = new ProductService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Product> products = productService.getProducts();
		req.setAttribute("products", products);
		req.getRequestDispatcher("/category.jsp").forward(req, resp);
	}
}
