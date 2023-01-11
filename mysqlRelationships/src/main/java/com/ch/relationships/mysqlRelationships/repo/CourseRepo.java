package com.ch.relationships.mysqlRelationships.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ch.relationships.mysqlRelationships.entity.Course;

public interface CourseRepo extends JpaRepository<Course, Integer> {

}
