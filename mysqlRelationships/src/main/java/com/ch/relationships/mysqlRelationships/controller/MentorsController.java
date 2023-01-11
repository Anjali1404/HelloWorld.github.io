package com.ch.relationships.mysqlRelationships.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ch.relationships.mysqlRelationships.dto.EnrollResponse;
import com.ch.relationships.mysqlRelationships.entity.Course;
import com.ch.relationships.mysqlRelationships.entity.LearnerEnrolled;
import com.ch.relationships.mysqlRelationships.entity.Mentor;
import com.ch.relationships.mysqlRelationships.repo.CourseRepo;
import com.ch.relationships.mysqlRelationships.repo.LearnerEnrolledRepo;
import com.ch.relationships.mysqlRelationships.repo.MentorRepo;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mentor")
public class MentorsController {

	@Autowired
	private MentorRepo mentorRepo;

	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private LearnerEnrolledRepo learnerEnrolledRepo;
	
	@GetMapping("/all")
	public Collection<Mentor> getAllMentors(){
		return mentorRepo.findAll();
	}

	@GetMapping("/course/{courseId}")
	public Collection<Mentor> getAllMentorsForCourse(@PathVariable int courseId) {
		return mentorRepo.findAllMentorsForCourse(courseId);
	}

	@GetMapping("/{mentor_id}")
	public Mentor getMentorById(@PathVariable int mentor_id) {
		return mentorRepo.getById(mentor_id);
	}

	@PostMapping("/enroll/{course_id}/{mentor_id}")
	public ResponseEntity<EnrollResponse> enrollCourseForMentor(@PathVariable("mentor_id") int mentor_id,
			@PathVariable("course_id") int course_id) {
		Boolean sameCourse = false;
		Course c = courseRepo.getById(course_id);
		Mentor m = mentorRepo.getById(mentor_id);
		EnrollResponse er = new EnrollResponse();
		HttpStatus h = HttpStatus.OK;
		List<Course> lc = m.getCourses();

		for (int i = 0; i < lc.size(); i++) {
			if (lc.get(i).getId() == course_id) {
				sameCourse = true;
				break;
			}
		}

		if (!sameCourse) {
			if (lc.size() < 5) {
				c.getAvailableMentors().add(m);
				courseRepo.save(c);
				er.setMessage("Done");

			} else {
				er.setMessage("You cannot enroll in more than 5 courses at a time");
				h = HttpStatus.NOT_ACCEPTABLE;
			}
		} else {
			er.setMessage("Already enrolled in this course");
			h = HttpStatus.NOT_ACCEPTABLE;
		}

		return new ResponseEntity<EnrollResponse>(er, h);

	}
	
	@GetMapping("/{mentor_id}/myassignments")
	public List<LearnerEnrolled> viewMentorAssignments(@PathVariable("mentor_id") int mentor_id){
		return learnerEnrolledRepo.findByMentor_id(mentor_id);
	}

}
