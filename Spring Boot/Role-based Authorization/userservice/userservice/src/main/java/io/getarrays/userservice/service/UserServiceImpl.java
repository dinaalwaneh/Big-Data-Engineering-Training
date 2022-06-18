package io.getarrays.userservice.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.getarrays.userservice.domain.Role;
import io.getarrays.userservice.domain.User;
import io.getarrays.userservice.repo.RoleRepo;
import io.getarrays.userservice.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService {

	  // creating a logger
    org.jboss.logging.Logger logger = LoggerFactory.logger(UserServiceImpl.class);
	@Autowired
	private UserRepo userRepo ;
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public User saveUser(User user) {
		 
		logger.infof("Saving new user {} to the database",user.getName());
		return userRepo.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		// TODO Auto-generated method stub
		logger.infof("Saving new user {} to the database",role.getName());
		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		// TODO Auto-generated method stub
		logger.infof("Adding role {} to user {} ",username,roleName);
		User user = userRepo.findByUsername(username);
		Role role = roleRepo.findByName(roleName);
		user.getRoles().add(role);
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		logger.infof("Fetching uder {} ",username);
		return userRepo.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		logger.infof("Fetching all users");
		return userRepo.findAll();
	}

}
