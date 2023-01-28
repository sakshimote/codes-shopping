package com.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Department;
import com.springboot.repository.DepartmentRespository;
@RestController
public class DepartmentController {
	@Autowired
	private DepartmentRespository departmentRepository;
	
	
	@PostMapping("/department")
	public Department addDepartment(@RequestBody Department department) {
		return departmentRepository.save(department);
	}
	@GetMapping("/department")
	public List<Department> getAllDepartments(){
		return departmentRepository.findAll();
	}

}
