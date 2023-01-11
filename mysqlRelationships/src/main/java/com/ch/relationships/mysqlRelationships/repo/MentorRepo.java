package com.ch.relationships.mysqlRelationships.repo;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ch.relationships.mysqlRelationships.entity.Mentor;

public interface MentorRepo extends JpaRepository<Mentor, Integer> {
	
	@Query(
			  value = "select Mentor.id,Mentor.name,Mentor.status from Mentor,course_mentors where course_mentors.course_id = :course_id and mentor.id = course_mentors.mentor_id", 
			  nativeQuery = true)
	Collection<Mentor> findAllMentorsForCourse(@Param("course_id") Integer course_id);
	
	List<Mentor> findByStatus(String status);
	Mentor findByName(String name);

}
