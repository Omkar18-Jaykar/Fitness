package com.oj.fitness.service;

import com.oj.fitness.dtos.RegisterRequest;
import com.oj.fitness.dtos.UserResponse;
import com.oj.fitness.model.User;
import com.oj.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;

	private UserResponse mappedToResponse(User savedUser) {
		UserResponse response = new UserResponse();
		
		response.setId(savedUser.getId());
		response.setEmail(savedUser.getEmail());
		response.setPassword(savedUser.getPassword());
		response.setFirstName(savedUser.getFirstName());
		response.setLastName(savedUser.getLastName());
		response.setCreatedAt(savedUser.getCreatedAt());
		response.setUpdatedAt(savedUser.getUpdatedAt());
		
		return response;
	}
	
	public UserResponse register(RegisterRequest request) {
		User user = User.builder()
			.email(request.getEmail())
			.firstName(request.getFirstName())
			.lastName(request.getLastName())
			.password(request.getPassword())
			.build();
		User savedUser = userRepository.save(user);
		
		return mappedToResponse(savedUser);
	}

	
}
