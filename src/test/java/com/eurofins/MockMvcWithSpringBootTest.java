package com.eurofins;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.eurofins.controller.EmployeeController;
import com.eurofins.model.Address;
import com.eurofins.model.Employee;
import com.eurofins.service.EmployeeService;

@WebMvcTest(EmployeeController.class)
public class MockMvcWithSpringBootTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeService employeeService;
	
	
	@Test
	public void testGetEmployeeID() throws Exception {
		
		//Arrange
				
				
				var employee = Employee.builder()
										.id(1)
										.email("shamnitjsr@gmail.com")
										.phone(1234567890)
										.address(Address.builder().city("Bangalore").country("NewYorkCity").street("123 street1").build())
										.name("Shambhu")
										.build();
				
				//Act
				when(employeeService.getEmployeeById(1)).thenReturn(employee);
				
				//Assert
				
				this.mockMvc.perform(get("/employee/1")).andExpect(status().isOk());
	}
	
	@Test
	public void testGetEmployee() throws Exception {
		
		//Arrange
				
				
				var employee = Employee.builder()
										.id(1)
										.email("shamnitjsr@gmail.com")
										.phone(1234567890)
										.address(Address.builder().city("Bangalore").country("NewYorkCity").street("123 street1").build())
										.name("Shambhu")
										.build();
				
				//Act
				when(employeeService.getEmployeeById(1)).thenReturn(employee);
				
				//Assert
				
				var result = this.mockMvc.perform(MockMvcRequestBuilders.get("/employee/1"))
						.andExpect(MockMvcResultMatchers.jsonPath("name").value("Shambhu"))
						.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void testGetEmployeeList() throws Exception {
		
		//Arrange
				
		//Entity
		
		List<Employee> employeeLists = new ArrayList<Employee>(List.of( 
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

				
				//Act
				when(employeeService.getAllEmployees()).thenReturn(employeeLists);
				
				//Assert
				
				var result = this.mockMvc.perform(MockMvcRequestBuilders.get("/employee"))
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Shambhu"))
						.andExpect(MockMvcResultMatchers.jsonPath("$[0].address.city").value("Bangalore"))
						.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
