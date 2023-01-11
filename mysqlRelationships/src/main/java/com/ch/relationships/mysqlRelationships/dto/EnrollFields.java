package com.ch.relationships.mysqlRelationships.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(onlyExplicitlyIncluded = true)
public class EnrollFields {
	
	private int learner_id;
	private int course_id;
	private int mentor_id;

}
