package com.oj.fitness.service;

import com.oj.fitness.dtos.ActivityRequest;
import com.oj.fitness.dtos.ActivityResponse;
import com.oj.fitness.model.Activity;
import com.oj.fitness.model.User;
import com.oj.fitness.repository.ActivityRepository;
import com.oj.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {
	
	private final ActivityRepository activityRepository;
	private final UserRepository userRepository;

	private ActivityResponse mappedToResponse(Activity savedActivity) {
		
		ActivityResponse response = new ActivityResponse();
		response.setId(savedActivity.getId());
		response.setUserId(savedActivity.getUser().getId());
		response.setType(savedActivity.getType());
		response.setDuration(savedActivity.getDuration());
		response.setCaloriesBurned(savedActivity.getCaloriesBurned());
		response.setStartTime(savedActivity.getStartTime());
		response.setAdditionalMatrics(savedActivity.getAdditionalMatrics());
		response.setCreatedAt(savedActivity.getCreatedAt());
		response.setUpdatedAt(savedActivity.getUpdatedAt());
		return response;
	}
	
	public ActivityResponse trackActivity(ActivityRequest activityRequest) {
		
		User user = userRepository.findById(activityRequest.getUserId())
			.orElseThrow(()-> new RuntimeException("Invalid User:- "+ activityRequest.getUserId()));
		
		Activity activity = Activity.builder()
			.user(user)
			.type(activityRequest.getType())
			.duration(activityRequest.getDuration())
			.caloriesBurned(activityRequest.getCaloriesBurned())
			.startTime(activityRequest.getStartTime())
			.additionalMatrics(activityRequest.getAdditionalMatrics())
			.build();
		
		Activity savedActivity = activityRepository.save(activity);
		
		return mappedToResponse(savedActivity);
	}


	public List<ActivityResponse> getUserActivities(String userId) {
		List<Activity> activityList = activityRepository.findByUserId(userId);
		return activityList.stream()
				.map(this::mappedToResponse)
				.collect(Collectors.toList());
			
	}
}
