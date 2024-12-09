package com.eurofins.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eurofins.model.Employee;
import com.eurofins.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	

	@RequestMapping("/employee")
	public List<Employee> getEmployee() {
		
		return  employeeService.getAllEmployees();
	}
	
	@RequestMapping("/employee/{id}")
	public Employee getEmployee(@PathVariable int id) {
		
		return  employeeService.getEmployeeById(id);
	}
	
	@PostMapping("/employee")
	public List<Employee> postEmployee(@RequestBody Employee employee){
		return employeeService.addEmployee(employee);
	}
	
	@PutMapping("/employee/{id}")
	public Employee putEmployee(@RequestBody Employee employee, @PathVariable int id) {
		return employeeService.updateEmployee(id, employee);
	}
	
	@DeleteMapping("/employee/{id}")
	public List<Employee> deleteEmployee(@PathVariable int id)
	{
		return employeeService.deleteEmployee(id);
	}

}
