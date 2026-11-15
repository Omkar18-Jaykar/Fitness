package com.oj.fitness.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RecommendationRequest {
	
	private String userId;
	private String activityId;
	private List<String> improvements;
	private List<String> suggestions;
	private List<String> safety;
}
