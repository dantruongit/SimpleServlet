package model;

import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {
	private String id, name_product, descriptions;
	private Timestamp created_at;
	private String image;
	
	public Product(ResultSet resultSet) {
		try {
			this.id = resultSet.getString("id");
			this.name_product = resultSet.getString("name_product");
			this.descriptions = resultSet.getString("descriptions");
			this.created_at = resultSet.getTimestamp("created_at");
			this.image = resultSet.getString("image");
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	public Product(String name_product, String descriptions, String image) {
		this.name_product = name_product;
		this.descriptions = descriptions;
		this.image = image;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName_product() {
		return name_product;
	}
	public void setName_product(String name_product) {
		this.name_product = name_product;
	}
	public String getDescriptions() {
		return descriptions;
	}
	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
