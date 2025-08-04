package com.userService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.userService.entity.User;
import com.userService.exception.UserNotFountException;
import com.userService.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User findUser(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFountException("user not found with id: " + id));
		return user;
	}

	@Override
	public User updateUser(Long id, User user) {
		User user1 = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFountException("user not found with id: " + id));
		user1.setEmail(user.getEmail());
		user1.setName(user.getName());
		return userRepository.save(user1);
	}


	@Override
	public Page<User> getAllUsers(Pageable pageable) {
		Page<User> allusers = userRepository.findAll(pageable);
		if(allusers.isEmpty()) {
			throw new UserNotFountException("users not found in this service");
		}
		return allusers;
	}

	@Override
	public void deleteUser(Long id) {
		if (!userRepository.existsById(id)) {
			throw new UserNotFountException("user not found with id: " + id);
		}
		userRepository.deleteById(id);
	}
}
