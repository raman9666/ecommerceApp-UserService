package com.userService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userService.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	

}
