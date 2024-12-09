package com.eurofins;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.eurofins.model.Address;
import com.eurofins.model.Employee;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringTestRestTemplateTests {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@LocalServerPort
	private int port;
	
	@Test
	void testGetEmployeeById() {
		//Arrange
		final String baseUrl = "http://localhost:" + port + "/employee/1";
		
		var employee = Employee.builder()
								.id(1)
								.email("shamnitjsr@gmail.com")
								.phone(1234567890)
								.address(Address.builder().city("Bangalore").country("NewYorkCity").street("123 street1").build())
								.name("Shambhu")
								.build();
		
		//Act
		var responseEntity  = this.testRestTemplate.getForObject(baseUrl, Employee.class);
		
		//Assert
		
		assertThat(responseEntity).isEqualTo(employee);
	}
	
	@Test
	public void testGetEmployees() {
		
		//Arrange
		final String baseUrl = "http://localhost:" + port + "/employee";
		
		var employee = Employee.builder()
				.id(1)
				.email("shamnitjsr@gmail.com")
				.phone(1234567890)
				.address(Address.builder().city("Bangalore").country("NewYorkCity").street("123 street1").build())
				.name("Shambhu")
				.build();
		
		//Act
		var responseEntity  = this.testRestTemplate.getForObject(baseUrl, Employee[].class);
		
		var responseEmployee = Arrays.stream(responseEntity).filter(x->x.getId()==1).findFirst().get();
		
		//Assert
		
		assertThat(responseEmployee).isEqualTo(employee);
		
	}
	
	@Test
	public void testPostEmployee() {
		
		//Arrange
		final String baseUrl = "http://localhost:" + port + "/employee";
		
		var employee = Employee.builder()
				.id(8)
				.email("john@gmail.com")
				.phone(8888888)
				.address(Address.builder().city("Chennai").country("India").street("888 street1").build())
				.name("John")
				.build();
		
		//Act
		var responseEntity = this.testRestTemplate.postForObject(baseUrl, employee, Employee[].class);
		var responseEmployee =  Arrays.stream(responseEntity).filter(x->x.getId()==8).findFirst().get();
		
		//Assert
		
		assertThat(responseEmployee).isEqualTo(employee);
		
	}

}
