package com.lms.api.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.dto.LearningTrackDto;
import com.lms.api.dto.UserDto;
import com.lms.api.model.Course;
import com.lms.api.model.Enroll;
import com.lms.api.model.LearningTrack;
import com.lms.api.model.User;
import com.lms.api.repository.CourseRepository;
import com.lms.api.repository.EnrollRepository;
import com.lms.api.repository.LearningTrackRepository;
import com.lms.api.repository.UserRepository;

@RestController
public class UserController {
	
	@Autowired
private UserRepository userRepository;
	@Autowired
	private LearningTrackRepository learningTrackRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private EnrollRepository enrollRepository; 
	@Autowired
	private CourseRepository courseRepository;
	
	@PostMapping("/user/register")
	public User register(@RequestBody User user) {
		String plainTextPass=user.getPassword();
		String encodedPass=passwordEncoder.encode(plainTextPass);
		user.setPassword(encodedPass);
		return userRepository.save(user);		
	}
	@GetMapping("/user/login")
	public UserDto login(Principal principal) {
		String username=principal.getName();
		User user=userRepository.findByName(username);
		UserDto dto=new UserDto();
		dto=dto.convert(user);
		return dto;
		
	}
	@PutMapping("/user/update/{id}")
	public void updateUserProfile(@PathVariable("id")Long id,@RequestBody User userNew) {
		
		User userDB=userRepository.getById(id);
		if(userNew.getName()!=null) 
			userDB.setName(userNew.getName());
		
		if(userNew.getMobile_no()!=null)
			userDB.setMobile_no(userNew.getMobile_no());
			
		if(userNew.getCity()!=null)
			userDB.setCity(userNew.getCity());
		
		
	}
	 
	/*
	 * enroll API
	 */
	
	@PostMapping("/enroll/user/learning-track/{uid}/{tid}")
	public Enroll enrollUserInLearningTrack(@PathVariable("uid") Long uid,@PathVariable("tid")Long tid) {
		User user=userRepository.getById(uid);
		LearningTrack learningTrack=learningTrackRepository.getById(tid);
		Enroll enroll=new Enroll();
		enroll.setUser(user);
		enroll.setLearning_track(learningTrack);
		enroll.setEnroll_date(LocalDate.now());
		enroll.setEnd_date(LocalDate.now().plusYears(1));
		
		return enrollRepository.save(enroll);
	}
	/*
	 * get all learning tracks with courses based on userId
	 */
	@GetMapping("/user/learning-track")
	public List<LearningTrackDto> getLearningTrackByuserID(Principal principal){
		List<LearningTrack> list=enrollRepository.getLearningTracksByUserID(principal.getName());
		List<LearningTrackDto> listDto=new ArrayList<>();
		/*
		 * for each learning tracks, fetch list of courses
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
