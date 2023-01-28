package com.lms.api.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;



@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
	

	@Column(nullable = false, unique = true)
private String question_text;
private int likes=0;

//@JsonBackReference
@ManyToMany(mappedBy = "question")
private List<Topic> topic;
@JsonIgnore
//@JsonManagedReference
@ManyToMany	
@JoinTable(name = "question_answer", 
joinColumns = @JoinColumn(name="question_id"),
inverseJoinColumns = @JoinColumn(name="answer_id"))
private List<Answer> answer;


public List<Topic> getTopic() {
	return topic;
}
public void setTopic(List<Topic> topic) {
	this.topic = topic;
}
public List<Answer> getAnswer() {
	return answer;
}
public void setAnswer(List<Answer> answer) {
	this.answer = answer;
}
private String username;

@Column(columnDefinition="DATE")
private LocalDate dateOfPost;



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
public String getQuestion_text() {
	return question_text;
}
public void setQuestion_text(String question_text) {
	this.question_text = question_text;
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
