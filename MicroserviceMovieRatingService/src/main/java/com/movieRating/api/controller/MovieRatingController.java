package com.movieRating.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieRating.api.model.Rating;
import com.movieRating.api.model.UserRating;


@RestController
@RequestMapping("/ratingsdata")
public class MovieRatingController {
	
	 //@RequestMapping("/movies/{movieId}")
	   // public Rating getMovieRating(@PathVariable("movieId") String movieId) {
	     //   return new Rating(movieId, 4);
	    //}

	    @RequestMapping("/user/{uid}")
	    public UserRating getUserRatings(@PathVariable("uid") String uid) {
	       List<Rating> ratings=Arrays.asList(
	    		   new Rating("100", 4),new Rating("200", 3),
	    		   new Rating("500", 5),new Rating("400", 2)
	    		   );
	       UserRating userRating=new UserRating();
	       userRating.setUserId(uid);
	       userRating.setRatings(ratings);
	       return userRating;
	    }
	    
	    
}
