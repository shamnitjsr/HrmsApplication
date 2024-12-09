package com.eurofins;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.eurofins.model.Address;
import com.eurofins.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestAssuredTests {

	@Value("${base.url}")
	private String baseUrl;
	
	@LocalServerPort
	private int port;
	
	@Test
	public void testGETEmployeeById() {
		
		given()
			.baseUri(baseUrl)
			.port(port)
			.basePath("/employee/2")
			.get().then().assertThat().body("name", Matchers.equalTo("Sayan"));
	}
	
	@Test
	public void testGetEmployees() {
		
		//Arrange
		
		var employee = Employee.builder()
				.id(1)
				.email("shamnitjsr@gmail.com")
				.phone(1234567890)
				.address(Address.builder().city("Bangalore").country("NewYorkCity").street("123 street1").build())
				.name("Shambhu")
				.build();
		
		//Act
		var response  = given().baseUri(baseUrl)
				.port(port)
				.basePath("/employee/1")
				.get();
		
		var responseEntity = response.body().as(Employee.class);
		
		
		
		//Assert
		
		assertThat(responseEntity).isEqualTo(employee);
		
	}
	
	
	@Test
	public void testPOSTEmployee() throws JsonProcessingException{
		//Arrange
		var employee = Employee.builder()
				.id(8)
				.email("john@gmail.com")
				.phone(8888888)
				.address(Address.builder().city("Chennai").country("India").street("888 street1").build())
				.name("John")
				.build();
		
		//Act
		
		var response = 
				given()
					.baseUri(baseUrl)
					.port(port)
					.basePath("/employee")
					.contentType("application/json")
					.body(employee)
					.post();
		
		
		var responseEntity = response.body().as(Employee[].class);
		var responseEmployee =  Arrays.stream(responseEntity).filter(x->x.getId()==8).findFirst().get();
		
		//Assert
		
		assertThat(responseEmployee).isEqualTo(employee);
		
	}
	
	@Test
	public void testPUTEmployee() throws JsonProcessingException{
		//Arrange
		var employee = Employee.builder()
				.id(3)
				.email("john@gmail.com")
				.phone(8888888)
				.address(Address.builder().city("Chennai").country("India").street("888 street1").build())
				.name("John")
				.build();
		
		//Act
		
		var response = 
				given()
					.baseUri(baseUrl)
					.port(port)
					.basePath("/employee/3")
					.contentType("application/json")
					.body(employee)
					.put();
		
		
		var responseEntity = response.body().as(Employee.class);
		
		
		//Assert
		
		assertThat(responseEntity).isEqualTo(employee);
		
	}
}
