package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Course;
import com.springboot.repository.CourseRepository;
@RestController
public class CourseController {
	@Autowired
	private CourseRepository courseRepository;
	
	@PostMapping("/course")
	public Course postInstructor(@RequestBody Course course) {
		return courseRepository.save(course);
	}
}
