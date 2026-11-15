package com.oj.fitness.service;

import com.oj.fitness.dtos.RecommendationRequest;
import com.oj.fitness.model.Activity;
import com.oj.fitness.model.Recommendation;
import com.oj.fitness.model.User;
import com.oj.fitness.repository.ActivityRepository;
import com.oj.fitness.repository.RecommendationRepository;
import com.oj.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {
	
	private final RecommendationRepository recommendationRepository;
	private final UserRepository userRepository;
	private final ActivityRepository activityRepository;
	
	public Recommendation generateRecommendation(RecommendationRequest request) {
		User user = userRepository.findById(request.getUserId())
			.orElseThrow(()-> new RuntimeException("user Not Found: "+request.getUserId()));

		Activity activity = activityRepository.findById(request.getActivityId())
			.orElseThrow(()-> new RuntimeException("Activity Not Found: "+request.getActivityId()));
	
		Recommendation recommendation = Recommendation.builder()
			.user(user)
			.activity(activity)
			.improvements(request.getImprovements())
			.suggestions(request.getSuggestions())
			.safety(request.getSafety())
			.build();
		
		return recommendationRepository.save(recommendation);
	}

	public List<Recommendation> getUserRecommendation(String userId) {
		return recommendationRepository.findByUserId(userId);
	}

	public List<Recommendation> getActivityRecommendation(String activityId) {
		return recommendationRepository.findByActivityId(activityId);
	}
}
