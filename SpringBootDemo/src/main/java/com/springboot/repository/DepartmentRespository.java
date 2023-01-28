package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.model.Department;

public interface DepartmentRespository extends JpaRepository<Department, Long> {

}
