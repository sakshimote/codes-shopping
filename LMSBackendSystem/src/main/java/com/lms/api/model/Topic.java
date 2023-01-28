package com.lms.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

private Long id;
	

	@Column(nullable = false, unique = true)
private String topic;
@JsonIgnore
//@JsonManagedReference	
@ManyToMany	
@JoinTable(name = "topic_question", 
joinColumns = @JoinColumn(name="topic_id"),
inverseJoinColumns = @JoinColumn(name="question_id"))
private List<Question> question;	
	
public List<Question> getQuestion() {
	return question;
}
public void setQuestion(List<Question> question) {
	this.question = question;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getTopic() {
	return topic;
}
public void setTopic(String topic) {
	this.topic = topic;
}


}
