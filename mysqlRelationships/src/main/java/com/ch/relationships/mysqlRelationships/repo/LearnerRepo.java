package com.ch.relationships.mysqlRelationships.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ch.relationships.mysqlRelationships.entity.Learner;

public interface LearnerRepo extends JpaRepository<Learner, Integer> {
	
	List<Learner> findByStatus(String status);
	Learner findByName(String name);

}
