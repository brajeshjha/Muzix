package com.stackroute.accountmanager.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.accountmanager.domain.User;
import com.stackroute.accountmanager.exception.UserAlreadyExistsException;
import com.stackroute.accountmanager.exception.UserNotFoundException;
import com.stackroute.accountmanager.repository.UserRepository;

@Service  
public class UserServiceImpl implements UserService {

	
	private UserRepository userRepo;

	@Autowired
	public UserServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	
	@Override
	public User findByUserIdAndPassword(String userId, String password) throws UserNotFoundException {
		User user = this.userRepo.findByUserIdAndPassword(userId, password);
		if (user == null) {
			throw new UserNotFoundException("Failed to sign in, username or password wrong");
		}

		return user;
	}
	
	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {
		Optional<User> optionalUser=this.userRepo.findById(user.getUserId());
		if(optionalUser.isPresent())
		{
			throw new UserAlreadyExistsException("User details cannot be saved . User already exist");
		}
			this.userRepo.save(user);
			return true;

	}


}

