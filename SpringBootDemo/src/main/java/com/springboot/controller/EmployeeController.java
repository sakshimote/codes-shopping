package com.springboot.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.model.Department;
import com.springboot.model.Employee;
import com.springboot.repository.DepartmentRespository;
import com.springboot.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DepartmentRespository departmentRepository;
	
	
	/*
	 * insert data in Employee
	 */
	@PostMapping("/employee/{did}")
	public Employee insertEmployee(@RequestBody Employee employee,
			@PathVariable("did") Long did) {
		Department d=departmentRepository.getById(did);
		employee.setDepartment(d);
		return employeeRepository.save(employee);
	}
	
	/*
	 * fetch all records
	 */
	@GetMapping("/employee")
	public List<Employee> getAllEmployee(
			@RequestParam(name="page",required = false,defaultValue = "0")Integer page,
			@RequestParam(name="size",required = false,defaultValue = "2")Integer size){
		
		Pageable pageable=PageRequest.of(page,size);
		return employeeRepository.findAll(pageable).getContent();
	}
	/*
	 * fetch single employee by id
	 */
	
	@GetMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable("id")long id) {
		Employee emp=employeeRepository.getById(id);
		return emp;
		}
	/*
	 * update existing employee record
	 */
	@PutMapping("/employee/{id}")
	public Employee updateEmployee(@PathVariable("id")Long id,@RequestBody Employee employeeNew) {
		/*
		 * step1:take the id of the employee that has to be updated:id
		 * step2:go to db and fetch record for this id:empdb
		 * step3:Read new Employee details/values from user :employeenew
		 * step4:update empdb with new values :empdb<---employeeNew
		 *
		 */
		
		Employee empDB=employeeRepository.getById(id); 
		if(employeeNew.getName()!=null) 
			empDB.setName(employeeNew.getName());
		
		if(employeeNew.getAge()!=0) 
			empDB.setAge(employeeNew.getAge());
		
		if(employeeNew.getCity()!=null) 
			empDB.setCity(employeeNew.getCity());
		
		if(employeeNew.getSalary()!=0) 
			empDB.setSalary(employeeNew.getSalary());
		
		if(employeeNew.getEmail()!=null) 
			empDB.setEmail(employeeNew.getEmail());
		
		return employeeRepository.save(empDB);
		
	}
	@DeleteMapping("/employee/{id}")
	public void deleteEmployee(@PathVariable("id") Long id) {
		employeeRepository.deleteById(id);
	}
	
	/*
	 * fetch list of employees by city
	 * passing request param in URL:localhost:8181/employee/city?city=london
	 */
	@GetMapping("/employee/city")
	public List<Employee> getEmployeeByCity(@RequestParam("city")String city){
		List<Employee> list=employeeRepository.findByCity(city);
		return list;
	}
	
	/*
	 *fetch list of employees having salary greater than >7000
	 */
	@GetMapping("/employee/salary")
	public List<Employee> getEmployeeBySalary(@RequestParam("salary")double salary){
		List<Employee> list=employeeRepository.findBySalaryGreaterThan(salary);
		return list;
			
	}
	
	/*
	 * fetch list of employees having age greater than 24
	 */
	@GetMapping("/employee/age")
	public List<Employee> getEmployeeByAge(@RequestParam("age") int age){
		List<Employee> list=employeeRepository.findByAgeGreaterThan(age);
		return list;
	}

	/*
	 * fetch list of employee by department
	 */
	@GetMapping("/employee/department/{did}")
	public List<Employee> getEmployeeByDepartment(@PathVariable("did") Long did){
		List<Employee> list=employeeRepository.findByDepartmentId(did);
		return list;
	}
	
}


/*
 *fetch: GETMapping
 *insert:POSTMapping
 *update:PUT
 *delete:DELETE 
 */
