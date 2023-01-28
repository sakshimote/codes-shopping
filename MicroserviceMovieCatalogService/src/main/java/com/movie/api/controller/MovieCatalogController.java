package com.movie.api.controller;



import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.movie.api.model.CatalogItem;
import com.movie.api.model.Movie;

import com.movie.api.model.UserRating;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String ServiceMovieCatalog="ServiceMovieCatalog";
	
	@RequestMapping("/{uid}")
	@CircuitBreaker(name = ServiceMovieCatalog,fallbackMethod ="getCatalogFallback")
	public List<CatalogItem> getCatalog(@PathVariable("uid")String uid){
		
		
		UserRating ratings=restTemplate.getForObject("http://movie-ratings-service/ratingsdata/user/"+uid,
				UserRating.class);
		
		return ratings.getRatings().stream()
		           .map(rating -> {
		               Movie movie=restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
		               return new CatalogItem(movie.getName(),movie.getDescription(),rating.getRating());
		           })
		           .collect(Collectors.toList());
	
}
	
	
	public List<CatalogItem> getCatalogFallback(@PathVariable("uid")String uid,Exception e){
		List a = new ArrayList();
		a.add(new CatalogItem("None","None",0));
		return a;
		
	}
}
