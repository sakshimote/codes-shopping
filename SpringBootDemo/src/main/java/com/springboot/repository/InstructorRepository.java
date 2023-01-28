package com.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.model.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
@Query("select i from Instructor i join i.course c where c.id=?1")
	List<Instructor> getInstructorByCourseId(Long cid);
@Query("select MAX(i.salary) as MaxSalary, MIN(i.salary) as MinSalary,COUNT(i.id) as cnt from Instructor i")
List<Double[]> getMaxSalary();

}

/*
 * List list=[{80000,88000,2.0}]
 * Double[] d=list.get(0);
 * d={80000,88000,2.0}
 * d[0]
 * d[1]
 * d[2]
 */
