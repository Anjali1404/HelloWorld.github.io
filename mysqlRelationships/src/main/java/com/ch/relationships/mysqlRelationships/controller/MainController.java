package com.ch.relationships.mysqlRelationships.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.ch.relationships.mysqlRelationships.dto.LoginReponse;
import com.ch.relationships.mysqlRelationships.dto.User;
import com.ch.relationships.mysqlRelationships.entity.Admin;
import com.ch.relationships.mysqlRelationships.entity.Course;
import com.ch.relationships.mysqlRelationships.entity.Learner;
import com.ch.relationships.mysqlRelationships.entity.Mentor;
import com.ch.relationships.mysqlRelationships.repo.AdminRepo;
import com.ch.relationships.mysqlRelationships.repo.CourseRepo;
import com.ch.relationships.mysqlRelationships.repo.LearnerRepo;
import com.ch.relationships.mysqlRelationships.repo.MentorRepo;

@RestController
@CrossOrigin(origins = "*")
public class MainController {
	
	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private LearnerRepo learnerRepo;

	@Autowired
	private MentorRepo mentorRepo;
	
	@Autowired
	private AdminRepo adminRepo;
	
	@GetMapping("/")
	public String healthCheck() {
		return "server is running";
	}
	
	@GetMapping("/courses")
	public List<Course> getAllCourses() {
		return courseRepo.findAll();
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User userData){
		String role = userData.getRole();
		LoginReponse loginReponse = new LoginReponse();
		HttpStatus h = HttpStatus.OK;
		if(role.equalsIgnoreCase("learner")) {
			Learner l = learnerRepo.findByName(userData.getName());
			loginReponse.setUserType("learner");
			loginReponse.setO(l);
		}else if(role.equalsIgnoreCase("mentor")) {
			Mentor m = mentorRepo.findByName(userData.getName());
			loginReponse.setUserType("mentor");
			loginReponse.setO(m);
		}else if(role.equalsIgnoreCase("admin")){
			Admin a = adminRepo.findByName(userData.getName());
			loginReponse.setUserType("admin");
			loginReponse.setO(a);
		}else {
			loginReponse.setMessage("no user found with this role");
			return new ResponseEntity<LoginReponse>(loginReponse,HttpStatus.NOT_FOUND);
		}
		
		if(loginReponse.getO() == null) {
			loginReponse.setMessage("either username or role is wrong");
			h = HttpStatus.NOT_FOUND;
		}else {
			loginReponse.setMessage("login Successful");
		}
		return new ResponseEntity<LoginReponse>(loginReponse,h);
	}
}
