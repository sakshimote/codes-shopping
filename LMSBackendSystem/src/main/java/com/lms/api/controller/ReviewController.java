package com.lms.api.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.lms.api.model.User;
import com.lms.api.dto.ReviewDto;
import com.lms.api.dto.ReviewStatsDto;
import com.lms.api.model.Course;
import com.lms.api.model.Review;
import com.lms.api.repository.CourseRepository;
import com.lms.api.repository.ReviewRepository;
import com.lms.api.repository.UserRepository;

@RestController
public class ReviewController {
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ReviewRepository reviewRepostory;
	@PostMapping("/review/{cid}")
	public Review postReview(@RequestBody Review review,@PathVariable("cid")Long cid,Principal principal) {
		/*
		 * go to CourseRepo and fetch course by id
		 */
		Course course=courseRepository.getById(cid);
		/*
		 * go to UserRepo and fetch user by username
		 */
		User user=userRepository.findByUsername(principal.getName());
		/*
		 * attach user and course to review obj
		 */
		review.setCourse(course);
		review.setUser(user);
		/*
		 * save review in db
		 */
		return reviewRepostory.save(review);
		
	}
	/*
	 * get all api for review 
	 */
	
	@GetMapping("/reviews")
	public List<Review> getAllReviews(){
		return reviewRepostory.findAll();
	}
	/*
	 * get review by course id
	 * {
	 * id
	 * content
	 * rating
	 * userId
	 * username
	 * name
	 * email
	 * courseid
	 * coursename
	 * learningtrackid
	 * learning track
	 * }
	 */
	@GetMapping("/review/{cid}")
	public List<ReviewDto>  getByCourseId(@PathVariable("cid")Long cid){
		
		List<Review> list=reviewRepostory.getByCourseId(cid);
		List<ReviewDto> dtoList=new ArrayList<>();
		for(Review review:list) {
			ReviewDto dto=new ReviewDto();
			
			dto.setId(review.getId());
			dto.setContent(review.getContent());
			dto.setRating(review.getRating());
			dto.setUserId(review.getUser().getId());
			dto.setUsername(review.getUser().getUsername());
			dto.setName(review.getUser().getName());
			dto.setEmail(review.getUser().getEmail_id());
			dto.setCourseId(review.getCourse().getId());
			dto.setCourseName(review.getCourse().getName());
			dto.setLearningId(review.getCourse().getLearning_track().getId());
			dto.setLearningTrackName(review.getCourse().getLearning_track().getName());
			
			dtoList.add(dto);
		}
		
		
		return dtoList; 
		
	}
	
	/*
	 * get review by course id sorted by rating (1-5) desc
	 */
	@GetMapping("/review/sort-rating/{cid}")
	public List<Review> getReviewByCourseIdSortedByRatingDesc(@PathVariable("cid")Long cid) {
		List<Review> list=reviewRepostory.findByCourseId(cid);
		List<Review> sortedList=list.stream().sorted(Comparator.comparingDouble(Review::getRating)
				.reversed()).collect(Collectors.toList());
		return sortedList;
	}
	
	/*
	 * update review : user that has created the review can update it 
	 */
	@PutMapping("/review/{rid}")
	public Review updateReview(Principal principal,@RequestBody Review review,@PathVariable("rid")Long rid) {
		String username=principal.getName();
		
		Review reviewDB=reviewRepostory.getById(rid);
		if(review.getRating()!=0)
			reviewDB.setRating(review.getRating());
		if(review.getContent()!=null)
			reviewDB.setContent(review.getContent());
	String userOwner= reviewDB.getUser().getUsername();
	if(!username.equalsIgnoreCase(userOwner)) 
		throw new RuntimeException("user not allowed to update");
	return reviewRepostory.save(reviewDB);

	}
	@GetMapping("/review/statsByCid/{cid}")
	public ReviewStatsDto getReviewStatsByCourseId(@PathVariable("cid")Long cid) {
		
		ReviewStatsDto dto=new ReviewStatsDto();
		List<Double> FiveStarList=new ArrayList<>();
		List<Double> FourStarList=new ArrayList<>();
		List<Double> ThreeStarList=new ArrayList<>();
		List<Double> TwoStarList=new ArrayList<>();
		List<Double> OneStarList=new ArrayList<>();
		
		
		List<Review> list=reviewRepostory.getByCourseId(cid);
		
		Long totalReviews=(long) list.size();
		
			FiveStarList=list.stream()
			.filter(r->r.getRating()==5).map(r->r.getRating()).collect(Collectors.toList());
			dto.setFiveStarrating(FiveStarList.size());
		
		
			 FourStarList=list.stream()
			.filter(r->r.getRating()==4).map(r->r.getRating()).collect(Collectors.toList());
			 dto.setFourStarRating(FourStarList.size());
		
		
			ThreeStarList=list.stream()
			.filter(r->r.getRating()==3).map(r->r.getRating()).collect(Collectors.toList());
			dto.setThreeStarRating(ThreeStarList.size());
		
		
			TwoStarList=list.stream()
			.filter(r->r.getRating()==2).map(r->r.getRating()).collect(Collectors.toList());
			dto.setTwoStarRating(TwoStarList.size());
		
		
			OneStarList=list.stream()
			.filter(r->r.getRating()==1).map(r->r.getRating()).collect(Collectors.toList());
		dto.setOneStarRating(OneStarList.size());
		
		
		
	dto.setTotalRating(totalReviews);
		return dto;
	}
}
