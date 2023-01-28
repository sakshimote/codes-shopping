package com.lms.api.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
	
	@Column(nullable = false, unique = true,length = 500)
private String answer_text;
	
private int likes=0;

private String username;

@JsonIgnore
//@JsonBackReference
@ManyToMany(mappedBy = "answer")
private List<Question> question;

@Column(columnDefinition="DATE")
private LocalDate dateOfPost;



public List<Question> getQuestion() {
	return question;
}
public void setQuestion(List<Question> question) {
	this.question = question;
}
public LocalDate getDateOfPost() {
	return dateOfPost;
}
public void setDateOfPost(LocalDate dateOfPost) {
	this.dateOfPost = dateOfPost;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getAnswer_text() {
	return answer_text;
}
public void setAnswer_text(String answer_text) {
	this.answer_text = answer_text;
}
public int getLikes() {
	return likes;
}
public void setLikes(int likes) {
	this.likes = likes;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}


}
