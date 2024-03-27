package api;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.invoke.ConstantBootstraps;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import common.Constaint;
import model.Product;
import payload.ResponseData;
import service.ProductService;


@WebServlet(name="ProductApi", urlPatterns = {Constaint.API_PRODUCT_CREATE, Constaint.API_PRODUCT_READ, Constaint.API_PRODUCT_UPDATE
		, Constaint.API_PRODUCT_DELETE})
public class ProductApi extends HttpServlet{
	private ProductService productService = new ProductService();
	private Gson gson = new Gson();
	
	private ResponseData getProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		ResponseData data = new ResponseData();
		String id = req.getParameter("id");
		if(id == null) {
			data.setSuccess(true);
			data.setData(productService.getProducts());
		}
		else {
			Product product = productService.getProductById(id);
			data.setSuccess(product != null);
			data.setData(product);
		}
		return data;
	}
	
	private ResponseData updateProduct (HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		ResponseData data = new ResponseData();
		String name_product = req.getParameter("name_product");
		String descriptions = req.getParameter("descriptions");
		String image = req.getParameter("image");
		String id = req.getParameter("id") == null ? "-1" : req.getParameter("id");
		Product product = productService.getProductById(id);
		if(product == null) {
			data.setSuccess(false);
			data.setData(null);
		}
		else {
			Product product_new = new Product(name_product, descriptions, image);
			data.setSuccess(productService.updateProductById(id, product_new));
			data.setData(product_new);
		}
		return data;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(),"UTF-8"));
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String urlPath = req.getServletPath();
		ResponseData data = new ResponseData();
		switch(urlPath) {
			case Constaint.API_PRODUCT_READ:{
				data = getProduct(req, resp);
				break;
			}
		}
		
		String json = gson.toJson(data);
		out.print(json);
		out.flush();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(),"UTF-8"));
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		String urlPath = req.getServletPath();
		ResponseData data = new ResponseData();
		switch(urlPath) {
			case Constaint.API_PRODUCT_READ:{
				data = getProduct(req, resp);
				break;
			}
			case Constaint.API_PRODUCT_UPDATE:{
				data = updateProduct(req, resp);
				break;
			}
		}
		
		String json = gson.toJson(data);
		out.print(json);
		out.flush();
	}
}
