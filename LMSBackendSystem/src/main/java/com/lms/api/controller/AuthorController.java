package com.lms.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lms.api.model.Author;
import com.lms.api.repository.AuthorRepository;



@RestController
public class AuthorController {
	@Autowired
	private AuthorRepository authorRepository;
	
	@PostMapping("/author/insert")
	public Author postAuthor(@RequestBody Author author) {
		return authorRepository.save(author);
	}
	
	
}
