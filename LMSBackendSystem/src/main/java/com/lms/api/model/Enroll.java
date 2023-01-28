package com.lms.api.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Enroll {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
	@Column(columnDefinition="DATE")
private LocalDate enroll_date;
	
	@Column(columnDefinition="DATE")
private LocalDate end_date;

@OneToOne
private User user;

@OneToOne
private LearningTrack learningTrack;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public LocalDate getEnroll_date() {
	return enroll_date;
}

public void setEnroll_date(LocalDate enroll_date) {
	this.enroll_date = enroll_date;
}

public LocalDate getEnd_date() {
	return end_date;
}

public void setEnd_date(LocalDate end_date) {
	this.end_date = end_date;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}

public LearningTrack getLearning_track() {
	return learningTrack;
}

public void setLearning_track(LearningTrack learningTrack) {
	this.learningTrack = learningTrack;
}


}
