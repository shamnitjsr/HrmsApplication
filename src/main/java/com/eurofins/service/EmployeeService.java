package com.eurofins.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.eurofins.model.Address;
import com.eurofins.model.Employee;

@Service
public class EmployeeService {
	
	//Entity
		private List<Employee> employeeLists = new ArrayList<Employee>(List.of( 
				Employee.builder()
				.id(1)
				.email("shamnitjsr@gmail.com")
				.phone(1234567890)
				.address(Address.builder().city("Bangalore").country("NewYorkCity").street("123 street1").build())
				.name("Shambhu")
				.build(),
		
				Employee.builder()
				.id(2)
				.email("shamnbhuk260@gmail.com")
				.phone(1234534890)
				.address(Address.builder().city("Newyork").country("USA").street("111 street111").build())
				.name("Sayan")
				.build(),
				
				Employee.builder()
				.id(3)
				.email("sayan260@gmail.com")
				.phone(1234534220)
				.address(Address.builder().city("San Francssco").country("USA").street("121 street121").build())
				.name("Sayan")
				.build()
				));

		public List<Employee> getAllEmployees() {
			
			return employeeLists;
		}

		public Employee getEmployeeById(int id) {
			
			return employeeLists.stream()
					.filter(x->x.getId() == id)
					.findFirst().get();
		}

		public List<Employee> addEmployee(Employee employee) {
			
			employeeLists.add(employee);
			return employeeLists;
		}
		
		public Employee updateEmployee(int id, Employee employee){
			
			return employeeLists.stream()
					.filter(x->x.getId() == id)
					.peek(o -> o.setName(employee.getName()))
					.peek(o -> o.setEmail(employee.getEmail()))
					.peek(o -> o.setPhone(employee.getPhone()))
					.peek(o -> o.setAddress(employee.getAddress()))
					.findFirst().get();
		}

		public List<Employee> deleteEmployee(int id) {
			employeeLists.removeIf(x->x.getId()==id);
			return employeeLists;
		}

}
