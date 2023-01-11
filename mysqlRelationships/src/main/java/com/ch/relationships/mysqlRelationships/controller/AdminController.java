package com.ch.relationships.mysqlRelationships.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ch.relationships.mysqlRelationships.entity.Course;
import com.ch.relationships.mysqlRelationships.entity.Learner;
import com.ch.relationships.mysqlRelationships.entity.LearnerEnrolled;
import com.ch.relationships.mysqlRelationships.entity.Mentor;
import com.ch.relationships.mysqlRelationships.repo.CourseRepo;
import com.ch.relationships.mysqlRelationships.repo.LearnerEnrolledRepo;
import com.ch.relationships.mysqlRelationships.repo.LearnerRepo;
import com.ch.relationships.mysqlRelationships.repo.MentorRepo;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private LearnerRepo learnerRepo;

	@Autowired
	private MentorRepo mentorRepo;
	
	@Autowired
	private LearnerEnrolledRepo learnerEnrolledRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
	
	@GetMapping("/report/activeLearner")
	public ResponseEntity<List<Learner>> listActiveLearners(){
		List<Learner> user = learnerRepo.findByStatus("Active");
			return new ResponseEntity<List<Learner>>(user,HttpStatus.OK);
	}
	
	@GetMapping("/report/inActiveLearner")
	public ResponseEntity<List<Learner>> listInActiveLearners(){
		List<Learner> user = learnerRepo.findByStatus("inactive");
			return new ResponseEntity<List<Learner>>(user,HttpStatus.OK);
	}
	
	@GetMapping("/report/activeMentor")
	public ResponseEntity<List<Mentor>> listActiveMentors(){
		List<Mentor> user = mentorRepo.findByStatus("Active");
			return new ResponseEntity<List<Mentor>>(user,HttpStatus.OK);
	}
	
	@GetMapping("/report/inActiveMentor")
	public ResponseEntity<List<Mentor>> listInActiveMentors(){
		List<Mentor> user = mentorRepo.findByStatus("inactive");
			return new ResponseEntity<List<Mentor>>(user,HttpStatus.OK);
	}
	
	@GetMapping("/report/allOngoingCourses")
	public ResponseEntity<List<LearnerEnrolled>> listAllOngoingCourses(){
		List<LearnerEnrolled> le = learnerEnrolledRepo.findAll();
		return new ResponseEntity<List<LearnerEnrolled>>(le,HttpStatus.OK);
	}
	
	@PostMapping("/addcourse")
	public ResponseEntity<Course> saveCourse(@RequestBody Course course){
		return new ResponseEntity<Course>(courseRepo.save(course), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deletecourse/{course_id}")
	public ResponseEntity<String> deleteCourse(@PathVariable("course_id") int course_id){
		
		courseRepo.deleteById(course_id);
		return new ResponseEntity<String>("Course deleted successfully!.", HttpStatus.OK);
	}
	
	@PostMapping("/addLearner")
	public ResponseEntity<Learner> saveUser(@RequestBody Learner learner){
		learner.setStatus("Active");
		Learner l = new Learner(learner.getName(),learner.getStatus());
		learnerRepo.save(l);
		return new ResponseEntity<Learner>(l, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteLearner/{learner_id}")
	public ResponseEntity<String> deleteUser(@PathVariable("learner_id") int learner_id){
		learnerRepo.deleteById(learner_id);
		return new ResponseEntity<String>("User deleted successfully!.", HttpStatus.OK);
	}

	@PostMapping("/addMentor")
	public ResponseEntity<Mentor> saveMentor(@RequestBody Mentor mentor){
		mentor.setStatus("Active");
		return new ResponseEntity<Mentor>(mentorRepo.save(mentor), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteMentor/{mentor_id}")
	public ResponseEntity<String> deleteMentor(@PathVariable("mentor_id") int mentor_id){
		mentorRepo.deleteById(mentor_id);
		return new ResponseEntity<String>("User deleted successfully!.", HttpStatus.OK);
	}
	
}
