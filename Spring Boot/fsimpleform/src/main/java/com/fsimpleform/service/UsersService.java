package com.fsimpleform.service;

import org.springframework.stereotype.Service;

import com.fsimpleform.model.UserModel;
import com.fsimpleform.repository.UsersRepository;

@Service 
public class UsersService {

	private final UsersRepository usersRepository ;
	
	public UsersService(UsersRepository usersRepository) {
		this.usersRepository=usersRepository;
	}
	
	public UserModel registerUser(String login , String password , String email) {
		if (login == null || password == null) {
			return null;
		}else {
			UserModel usersModel = new UserModel();
			usersModel.setLogin(login);
			usersModel.setPassword(password);
			usersModel.setEmail(email);
			return usersRepository.save(usersModel);
		}
	}
	
	public UserModel authenticate(String login , String password) {
		return usersRepository.findByLoginAndPassword(login, password).orElse(null);
		
	}
}
