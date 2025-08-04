package com.userService.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.userService.entity.User;

public interface UserService {
	
	
	public User saveUser(User user);
	public User findUser(Long id);
	public User updateUser(Long id,User user);
	public Page<User> getAllUsers(Pageable request);
	public void deleteUser(Long id);

}
