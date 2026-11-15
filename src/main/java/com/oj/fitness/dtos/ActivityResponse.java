package com.oj.fitness.dtos;

import com.oj.fitness.model.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActivityResponse {
	private String id;
	private String userId;
	private ActivityType type;
	private Map<String, Object> additionalMatrics;
	private Integer duration;
	private Integer caloriesBurned;
	private LocalDateTime startTime;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
