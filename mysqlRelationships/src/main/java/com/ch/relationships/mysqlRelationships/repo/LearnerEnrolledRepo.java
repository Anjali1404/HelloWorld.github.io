package com.ch.relationships.mysqlRelationships.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ch.relationships.mysqlRelationships.entity.LearnerEnrolled;

public interface LearnerEnrolledRepo extends JpaRepository<LearnerEnrolled, Integer> {
	@Query(
			  value = "select * from learner_enrolled where mentor_id = :mentorid", 
			  nativeQuery = true)
	List<LearnerEnrolled> findByMentor_id(@Param("mentorid") int mentor_id);
	
	@Query(
			  value = "select * from learner_enrolled where mentor_id = :mentorid and status = :status", 
			  nativeQuery = true)
	List<LearnerEnrolled> findByMentor_idandStatus(@Param("mentorid") int mentor_id, @Param("status") String status);
	
	@Query(
			  value = "select * from learner_enrolled where learner_id = :learnerId", 
			  nativeQuery = true)
	List<LearnerEnrolled> findByLearner_id(@Param("learnerId") int learner_id);

}
