package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.MySqlConnection;
import model.Product;

public class ProductRepository {
	public int insertProduct(Product product) {
		Connection connection = MySqlConnection.getConnection();
		String query = "insert into Product(id, name_product, descriptions, image) values(?, ?, ?, ?);";
		int isInsert = 0;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, product.getId());
			statement.setString(2, product.getName_product());
			statement.setString(3, product.getDescriptions());
			statement.setString(4, product.getImage());
			isInsert = statement.executeUpdate();
			statement.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isInsert;
	}
	
	public List<Product> getAllProducts(){
		List<Product> products = new ArrayList<>();
		Connection connection = MySqlConnection.getConnection();
		String query = "select * from products";
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) products.add(new Product(resultSet));
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO: handle exception
		}
		finally {
			try {
				connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return products;
	}
	
	public Product getProductById(String id) {
		Product product = null;
		Connection connection = MySqlConnection.getConnection();
		String query = "select * from products where id = ?;";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, id);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) product = new Product(resultSet);
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return product;
	}
	
	public int updateProductById(String id, Product product_new) {
		Connection connection = MySqlConnection.getConnection();
		int isUpdate = 0;
		String query = "update products set name_product = ?, descriptions = ?, image = ? where id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, product_new.getName_product());
			statement.setString(2, product_new.getDescriptions());
			statement.setString(3, product_new.getImage());
			statement.setString(4, id);
			isUpdate = statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return isUpdate;
	}
	
	public int deleteProductById(String id) {
		Connection connection = MySqlConnection.getConnection();
		int isDelete = 0;
		String query = "delete from products p where p.id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, id);
			isDelete = statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDelete;
	}
}
