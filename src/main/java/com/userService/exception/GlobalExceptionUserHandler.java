package com.userService.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionUserHandler {
	
	
	@ExceptionHandler(UserNotFountException.class)
	public ResponseEntity<Map<String,Object>> userNotFoundExceptionHandler(UserNotFountException ex) {
		Map<String, Object> errors=new HashMap<>();
		errors.put("time stamp", LocalDateTime.now());
		errors.put("status", HttpStatus.NOT_FOUND.value());
		errors.put("error", "No user exception");
		errors.put("message", ex.getMessage());
		return ResponseEntity.ok(errors);
	}

}
