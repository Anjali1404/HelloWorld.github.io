package com.ch.relationships.mysqlRelationships.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Table(name = "learner_enrolled")
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class LearnerEnrolled implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -641423396418227691L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="learner_id")
	private Learner learner;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="course_id")
	private Course course;
	
	@Column(name="mentor_id")
	private int mentor_id; 
	
	@Column(name = "status")
	private String status;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LearnerEnrolled)) return false;
        LearnerEnrolled that = (LearnerEnrolled) o;
        return Objects.equals(learner.getName(), that.learner.getName()) &&
                Objects.equals(course.getName(), that.course.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(learner.getName(), course.getName());
    }


	public LearnerEnrolled(Course c1, Learner learner, int mentor_id2, String status) {
		// TODO Auto-generated constructor stub
		this.course = c1;
		this.learner = learner;
		this.mentor_id = mentor_id2;
		this.status = status;
	}
}
