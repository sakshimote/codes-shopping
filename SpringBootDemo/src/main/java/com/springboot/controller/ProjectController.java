package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Project;
import com.springboot.repository.ProjectRepository;
@RestController
public class ProjectController {
	@Autowired
	private ProjectRepository projectRepository;
	
	@PostMapping("/project")
	public Project postProject(@RequestBody Project project) {
		return projectRepository.save(project);
	}
	
	@GetMapping("/project")
	public List<Project> getAllProjects(){
		return projectRepository.findAll();
	}
	
	@GetMapping("/project/{pid}")
	public Project getProjectById(@PathVariable("pid")Long pid) {
		return projectRepository.getById(pid);
	}
	@DeleteMapping("/project/{pid}")
	public void deleteProject(@PathVariable("pid")Long pid) {
		projectRepository.deleteById(pid);
	}
	
	

}
