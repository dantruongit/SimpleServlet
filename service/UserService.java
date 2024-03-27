package service;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import model.User;
import repository.UserRepository;

public class UserService {
	private UserRepository userRepository = new UserRepository();
	
	public boolean checkLogin(String username, String password) {
		return userRepository.getUserByUsrAndPwd(username, password) != null;
	}
	
	public User getUserByUsername(String username) {
		User user = userRepository.getUserByUsername(username);
		return user;
	}
}
