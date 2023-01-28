package com.lms.api.dto;



public class ReviewDto {
	
	private Long id;
	private String content;
	private double rating;
	private Long userId;
	private String username;
	private String name;
	private String email;
	private Long courseId;
	private String courseName;
	private Long learningId;
	private String learningTrackName;
	
	

	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Long getCourseId() {
		return courseId;
	}



	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}



	public String getCourseName() {
		return courseName;
	}



	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}



	public Long getLearningId() {
		return learningId;
	}



	public void setLearningId(Long learningId) {
		this.learningId = learningId;
	}



	public String getLearningTrackName() {
		return learningTrackName;
	}



	public void setLearningTrackName(String learningTrackName) {
		this.learningTrackName = learningTrackName;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public double getRating() {
		return rating;
	}



	public void setRating(double rating) {
		this.rating = rating;
	}

	
}
