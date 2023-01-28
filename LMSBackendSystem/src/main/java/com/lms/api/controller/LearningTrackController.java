package com.lms.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.lms.api.dto.LearningTrackDto;
import com.lms.api.model.Author;
import com.lms.api.model.Course;
import com.lms.api.model.LearningTrack;
import com.lms.api.repository.AuthorRepository;
import com.lms.api.repository.CourseRepository;
import com.lms.api.repository.LearningTrackRepository;


@RestController
public class LearningTrackController {
	
	@Autowired
private LearningTrackRepository learningTrackRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private AuthorRepository authorRepository;
	
	
	@PostMapping("/learningTrack/insert")
	public LearningTrack postLearningTrack(@RequestBody LearningTrack learningTrack) {
		return learningTrackRepository.save(learningTrack);
	}
	
	@PostMapping("/course/insert/{ltid}")
	public Course addCourse(@RequestBody Course course,@PathVariable("ltid") Long ltid) {
		LearningTrack learningTrack = learningTrackRepository.getById(ltid);
		
		if(learningTrack != null)
			course.setLearning_track(learningTrack);
		
		return courseRepository.save(course);
	}

	
	
	@PostMapping("/course/author/{cid}/{aid}")
	public Course assignAuthor(@PathVariable("cid") long cid, @PathVariable("aid") long aid) 
	{
		Course course = courseRepository.getById(cid);
		Author author = authorRepository.getById(aid);
		List<Author> authorList = course.getAuthor();
		authorList.add(author);
		course.setAuthor(authorList);
	return courseRepository.save(course);
	}
	

	@PutMapping("/course/author/{cid}/{aid}")
	public Course unAssignAuthor(@PathVariable("cid") long cid, @PathVariable("aid") long aid) 
	{
		Course course = courseRepository.getById(cid);
		Author author = authorRepository.getById(aid);
		List<Author> authorList = course.getAuthor();
		authorList.remove(author);
		course.setAuthor(authorList);
	return courseRepository.save(course);
	}
	
	@GetMapping("/learning-track")
	public List<LearningTrackDto> getLearningTrackWithCourse(){
		//fetch all learning tracks
		
		List<LearningTrack> list=learningTrackRepository.findAll();
		List<LearningTrackDto> listDto=new ArrayList<>();
		/*
		 * for each learning track , fetch list of courses
		 */
		
		list.stream().forEach(lt->{
			LearningTrackDto dto=new LearningTrackDto();
			List<Course> listCourse=courseRepository.findByLearningTrackId(lt.getId());
			dto.setId(lt.getId());
			dto.setName(lt.getName());
			dto.setCourse(listCourse);
			listDto.add(dto);
			
		});
		return listDto;
		}
		
		
		
	}
	

