package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;

import config.MySqlConnection;
import model.*;

public class UserRepository {
	public int insertUser(User user) {
		int isInsert = 0;
		Connection connection = MySqlConnection.getConnection();
		String query = "insert into User(username, password) values(?, ?);";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user.getUsr());
			statement.setString(2, user.getPwd());
			isInsert = statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isInsert;
	}
	
	public User getUserByUsrAndPwd(String usr, String pwd) {
		Connection connection = config.MySqlConnection.getConnection();
		String query = "SELECT * FROM users u where u.usr = ? and u.pwd = ?";
		User user = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, usr);
			preparedStatement.setString(2, pwd);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				user =  new User(resultSet);
			}
			connection.close();
		} catch (SQLException e) {
			return null;
		}
		return user;
	}
	
	public int updateUser(int idUser, User userNew) {
		int isUpdate = 0;
		Connection connection = MySqlConnection.getConnection();
		String query = "update User set username = ?, password = ? where id = ?";
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, userNew.getUsr());
			statement.setString(2, userNew.getPwd());
			statement.setInt(3, idUser);
			isUpdate = statement.executeUpdate();
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUpdate;
	}
	
}
