package service;

import java.util.List;
import model.Product;
import repository.ProductRepository;

public class ProductService {
	private ProductRepository productRepository = new ProductRepository();
	public List<Product> getProducts() {
		return productRepository.getAllProducts();
	}
	
	public Product getProductById(String id) {
		return productRepository.getProductById(id);
	}
	
	public boolean updateProductById(String id, Product product_new) {
		return productRepository.updateProductById(id, product_new) > 0;
	}
	
	public boolean deteleProductById(String id) {
		return productRepository.deleteProductById(id) > 0;
	}
}
