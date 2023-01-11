package com.ch.relationships.mysqlRelationships.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class learnerCourses {
	private int training_id;
	private int course_id;
	private String name;
	private String status;
	private int mentor_id;
}
