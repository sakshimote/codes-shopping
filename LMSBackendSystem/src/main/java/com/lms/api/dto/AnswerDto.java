package com.lms.api.dto;

import java.time.LocalDate;
import java.util.List;

import com.lms.api.model.Answer;


public class AnswerDto {
private Long questionId;
private String questionText;
private int likes;
private String qUsername;
private LocalDate dateOfPostQ;
private int numberOfAnswers;

private List<Answer> answers;



public int getLikes() {
	return likes;
}
public void setLikes(int likes) {
	this.likes = likes;
}
public List<Answer> getAnswers() {
	return answers;
}
public void setAnswers(List<Answer> answers) {
	this.answers = answers;
}
public Long getQuestionId() {
	return questionId;
}
public void setQuestionId(Long questionId) {
	this.questionId = questionId;
}
public String getQuestionText() {
	return questionText;
}
public void setQuestionText(String questionText) {
	this.questionText = questionText;
}
public String getqUsername() {
	return qUsername;
}
public void setqUsername(String qUsername) {
	this.qUsername = qUsername;
}
public LocalDate getDateOfPostQ() {
	return dateOfPostQ;
}
public void setDateOfPostQ(LocalDate dateOfPostQ) {
	this.dateOfPostQ = dateOfPostQ;
}
public int getNumberOfAnswers() {
	return numberOfAnswers;
}
public void setNumberOfAnswers(int numberOfAnswers) {
	this.numberOfAnswers = numberOfAnswers;
}

public static class TopicStats{
	private int totalNoTopics;
	private int totalNoOfQuestions;
	private int totalNoOfAnswers;
	private int totalNoOfUsers;
	public int getTotalNoTopics() {
		return totalNoTopics;
	}
	public void setTotalNoTopics(int totalNoTopics) {
		this.totalNoTopics = totalNoTopics;
	}
	public int getTotalNoOfQuestions() {
		return totalNoOfQuestions;
	}
	public void setTotalNoOfQuestions(int totalNoOfQuestions) {
		this.totalNoOfQuestions = totalNoOfQuestions;
	}
	public int getTotalNoOfAnswers() {
		return totalNoOfAnswers;
	}
	public void setTotalNoOfAnswers(int totalNoOfAnswers) {
		this.totalNoOfAnswers = totalNoOfAnswers;
	}
	public int getTotalNoOfUsers() {
		return totalNoOfUsers;
	}
	public void setTotalNoOfUsers(int totalNoOfUsers) {
		this.totalNoOfUsers = totalNoOfUsers;
	}

}
}
