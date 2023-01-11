package com.ch.relationships.mysqlRelationships.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ch.relationships.mysqlRelationships.dto.EnrollFields;
import com.ch.relationships.mysqlRelationships.dto.EnrollResponse;
import com.ch.relationships.mysqlRelationships.dto.LearnerResponse;
import com.ch.relationships.mysqlRelationships.dto.learnerCourses;
import com.ch.relationships.mysqlRelationships.entity.Course;
import com.ch.relationships.mysqlRelationships.entity.Learner;
import com.ch.relationships.mysqlRelationships.entity.LearnerEnrolled;
import com.ch.relationships.mysqlRelationships.repo.CourseRepo;
import com.ch.relationships.mysqlRelationships.repo.LearnerEnrolledRepo;
import com.ch.relationships.mysqlRelationships.repo.LearnerRepo;
import com.ch.relationships.mysqlRelationships.repo.MentorRepo;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/learner")
public class LearnerController {

	@Autowired
	private LearnerRepo learnerRepo;

	@Autowired
	private MentorRepo mentorRepo;

	@Autowired
	private CourseRepo courseRepo;

	@Autowired
	private LearnerEnrolledRepo learnerEnrolledRepo;

	@GetMapping("/all")
	public List<Learner> getAllLearners() {
		return learnerRepo.findAll();
	}
	
	@GetMapping("/{learner_id}")
	public LearnerResponse getLearnersDetails(@PathVariable("learner_id") int learner_id) {
		Learner lr =  learnerRepo.findById(learner_id).get();
		LearnerResponse learnerResponse = new LearnerResponse();
		learnerResponse.setId(lr.getId());
		learnerResponse.setName(lr.getName());
		learnerResponse.setStatus(lr.getStatus());
		List<LearnerEnrolled> ll = learnerEnrolledRepo.findByLearner_id(learner_id);
		learnerResponse.setCourses(ll);
		System.out.println(ll);
		return learnerResponse;
	}

	@PostMapping("/enroll")
	public ResponseEntity<EnrollResponse> enroll(@RequestBody EnrollFields enrollFields) {
		int activeCount = 0;
		Boolean mentorMatched = false;
		EnrollResponse res = new EnrollResponse();
		Course c1 = courseRepo.getById(enrollFields.getCourse_id());
		Learner l = learnerRepo.getById(enrollFields.getLearner_id());
		List<LearnerEnrolled> mentorCourses = learnerEnrolledRepo.findByMentor_idandStatus(enrollFields.getMentor_id(),
				"Active");
		List<LearnerEnrolled> le = l.getLearnerEnrolled();
		for (int i = 0; i < le.size(); i++) {
			if (!le.get(i).getStatus().equalsIgnoreCase("completed")) {
				activeCount++;
				if (le.get(i).getMentor_id() == enrollFields.getMentor_id()) {
					mentorMatched = true;
				}
			}

		}

		if (mentorCourses.size() < 10) {
			if (activeCount < 3) {
				if (mentorMatched == false) {
					LearnerEnrolled lee = new LearnerEnrolled(c1, l, enrollFields.getMentor_id(), "Active");
					Learner lr = learnerRepo.save(new Learner(l.getId(), l.getName(), l.getStatus(), lee));
					System.out.println(lr);
					res.setMessage("Done");
				} else {
					res.setMessage(
							"Mentor is already assigned to you for another course. Please select different mentor");
				}
			} else {
				res.setMessage("There can be only 3 'in progress' courses at a time for a user");
			}
		} else {
			res.setMessage("Mentor Quota full, please choose different mentor");
		}

		return new ResponseEntity<EnrollResponse>(res, HttpStatus.OK);
	}

}
