package com.ch.relationships.mysqlRelationships.dto;

import java.util.List;
import com.ch.relationships.mysqlRelationships.entity.LearnerEnrolled;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LearnerResponse {
	private int id;
	private String name;
	private String status;
	private List<LearnerEnrolled> courses;
}
