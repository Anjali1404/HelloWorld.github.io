package com.ch.relationships.mysqlRelationships.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
//@ToString(onlyExplicitlyIncluded = true)
public class Learner {
	
	public Learner(int id2, String name2, String status, LearnerEnrolled learnerEnrolled) {
		this.id = id2;
		this.name = name2;
		this.status = status;
		this.learnerEnrolled.add(learnerEnrolled);
	}
	
	public Learner(String name,String status) {
		this.name = name;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String status;
	@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
    @OneToMany(mappedBy = "learner", cascade = CascadeType.ALL)
    private List<LearnerEnrolled> learnerEnrolled= new ArrayList<>();

}
