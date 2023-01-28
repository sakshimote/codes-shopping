package com.lms.api.dto;

import java.util.List;


public class TopicDto {
	
	private Long topicId;
	private String topicname;
	private int numberOfQuestions;
	private List<questionDto> questions;
	
	
	public List<questionDto> getQuestions() {
		return questions;
	}
	public void setQuestions(List<questionDto> questions) {
		this.questions = questions;
	}
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	public String getTopicname() {
		return topicname;
	}
	public void setTopicname(String topicname) {
		this.topicname = topicname;
	}
	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}
	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}
	public static class questionDto{
	
		private Long id;
		private String text;
		private int likes;
		private String username;
		private int numberOfAnswers;
		
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
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
		public int getNumberOfAnswers() {
			return numberOfAnswers;
		}
		public void setNumberOfAnswers(int numberOfAnswers) {
			this.numberOfAnswers = numberOfAnswers;
		}
		

			
		
		
		
	}
	

}
