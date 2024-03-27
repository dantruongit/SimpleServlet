package service;

import repository.UserRepository;

public class LoginService {
	private UserRepository userRepository = new UserRepository();
	public boolean checkLogin(String username, String password) {
		return userRepository.getUserByUsrAndPwd(username, password) != null;
	}
}
