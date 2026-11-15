package com.oj.fitness.controller;

import com.oj.fitness.dtos.RegisterRequest;
import com.oj.fitness.dtos.UserResponse;
import com.oj.fitness.model.User;
import com.oj.fitness.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AuthController {
	
	private final UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest registerRequest){
		UserResponse response = userService.register(registerRequest);
		return ResponseEntity.ok(response);
	}
}
