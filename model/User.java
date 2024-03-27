package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/*
 * create table users(
	id int auto_increment,
    usr varchar(100),
    pwd varchar(100),
    created_at timestamp default now(),
    primary key(id)
);*/
public class User {
	private int id;
	private String usr, pwd;
	private Timestamp created_at;
	
	public User(ResultSet resultSet) {
		try {
			this.setId(resultSet.getInt("id"));
			this.setUsr(resultSet.getString("username"));
			this.setPwd(resultSet.getString("password"));
			this.setCreated_at(resultSet.getTimestamp("created_at"));
		} catch (SQLException e) {
			// TODO: handle exception
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsr() {
		return usr;
	}
	public void setUsr(String usr) {
		this.usr = usr;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp timestamp) {
		this.created_at = timestamp;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", usr=" + usr + ", pwd=" + pwd + ", created_at=" + created_at + "]";
	}
	
	
	
	
}
