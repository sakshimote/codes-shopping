package com.lms.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;
	
	@Column(nullable = false)
private String name;
	
	@Column(nullable = false, unique = true)
private String mobile_no;
	
	@Column(nullable = false, unique = true)
private String email_id;
	
	@Column(nullable = true)
private String city;
	@Column(nullable = false, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String role;
	

public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
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
public String getMobile_no() {
	return mobile_no;
}
public void setMobile_no(String mobile_no) {
	this.mobile_no = mobile_no;
}
public String getEmail_id() {
	return email_id;
}
public void setEmail_id(String email_id) {
	this.email_id = email_id;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}


}
