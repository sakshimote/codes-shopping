package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.dtos.StatsDto;
import com.springboot.model.Course;
import com.springboot.model.Instructor;
import com.springboot.repository.CourseRepository;
import com.springboot.repository.InstructorRepository;
@RestController
public class InstructorController {
	@Autowired
private InstructorRepository instructorRepository;
	@Autowired
	private CourseRepository courseRepository;
	
	@PostMapping("/instructor")
	public Instructor postInstructor(@RequestBody Instructor instructor) {
		return instructorRepository.save(instructor);
	}
	
	@PostMapping("/instructor/course/{iid}/{cid}")
	public Instructor assignInstructorToCourse(@PathVariable("iid")Long iid,
			@PathVariable("cid")Long cid) {
		Instructor instructor=instructorRepository.getById(iid);
		List<Course> courses=instructor.getCourse();
		
		Course course=courseRepository.getById(cid);
		courses.add(course);
		return instructorRepository.save(instructor);
	}
	
	@GetMapping("/instructor/course/{cid}")
	public List<Instructor> getInstructorByCourseId(@PathVariable("cid")Long cid){
		List<Instructor> list=instructorRepository.getInstructorByCourseId(cid);
		return list;
		
	}
	
	@GetMapping("/instructor/max/salary")
	public StatsDto getMaxInstructorSalary() {
		List<Double[]> list=instructorRepository.getMaxSalary();
		Double[] stats=list.get(0);
		
		double max=stats[0];
		double min=stats[1];
		double count=stats[2];
		
		StatsDto dto =new StatsDto();
		dto.setCount(count);
		dto.setMax(max);
		dto.setMin(min);
		return dto;
	}
}
