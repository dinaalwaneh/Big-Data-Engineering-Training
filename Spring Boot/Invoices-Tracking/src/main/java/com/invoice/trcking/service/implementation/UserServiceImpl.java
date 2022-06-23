package com.invoice.trcking.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.trcking.model.User;
import com.invoice.trcking.repository.UserRepository;
import com.invoice.trcking.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;


	@Override
	public List<User> getAllUsers() {
		List<User> listOfUsers = new ArrayList<User>();
	    userRepository.findAll().forEach(user-> listOfUsers.add(user));
	    return listOfUsers;
	}

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(long id, User newUserDetails) {
		User user = userRepository.findById(id).get();
	
		//.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		
		user.setName(newUserDetails.getName());
		user.setEnabled(newUserDetails.getEnabled());
		user.setUsername(newUserDetails.getUsername());
		return userRepository.save(user);
	}

	@Override
	public User getUserById(long id) {
		Optional<User> result = userRepository.findById(id);
		return result.get();
		/*if(result.isPresent()) {
			return result.get();
		}else {
			throw new ResourceNotFoundException("Post", "id", id);
		}*/
		
//		Post post = postRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		//return post;
	}
	

}
