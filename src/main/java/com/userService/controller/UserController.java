package com.userService.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userService.entity.User;
import com.userService.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/saveuser")
	public ResponseEntity<Map<String, Object>> saveUser(@RequestBody User data) {
		User saveUser = userService.saveUser(data);
		Map<String, Object> responce = new HashMap<>();
		responce.put("time stamp", LocalDateTime.now());
		responce.put("status", HttpStatus.NOT_FOUND.value());
		responce.put("message", "user data saved succussfully");
		responce.put("user", saveUser);
		return ResponseEntity.ok(responce);
	};

	@PutMapping("/update/{id}")
	public ResponseEntity<Map<String, Object>> updateUser(@PathVariable Long id, @RequestBody User user) {
		User updateUser = userService.updateUser(id, user);
		Map<String, Object> responce = new HashMap<>();
		responce.put("time stamp", LocalDateTime.now());
		responce.put("status", HttpStatus.NOT_FOUND.value());
		responce.put("message", "user data updated succussfully");
		responce.put("user", updateUser);
		return ResponseEntity.ok(responce);
	};

	@GetMapping("/getuser/{id}")
	public ResponseEntity<User> getUserByid(@PathVariable Long id) {
		User user = userService.findUser(id);
		return ResponseEntity.ok(user);
	};

	@GetMapping("/getall")
	public ResponseEntity<Page<User>> getAllUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<User> allUsers = userService.getAllUsers(pageRequest);
		return ResponseEntity.ok(allUsers);
	};
    @DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String,Object>> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		Map<String, Object> responce = new HashMap<>();
		responce.put("time stamp", LocalDateTime.now());
		responce.put("status", HttpStatus.NOT_FOUND.value());
		responce.put("message", "user data delete succussfully");
		responce.put("deleted id", id);
		return ResponseEntity.ok(responce);
	};

}
