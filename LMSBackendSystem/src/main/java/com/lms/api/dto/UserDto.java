package com.lms.api.dto;

import com.lms.api.model.User;

public class UserDto {
private Long id;
	private String name;
	private String email;
	private String mobile;
	private String role;
	private String username;
	private String city;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public UserDto convert(User user) {
		// TODO Auto-generated method stub
		UserDto dto=new UserDto();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setCity(user.getCity());
		dto.setEmail(user.getEmail_id());
		dto.setMobile(user.getMobile_no());
		dto.setUsername(user.getUsername());
		dto.setRole(user.getRole());
		return dto;
	}
	
	
	
}
