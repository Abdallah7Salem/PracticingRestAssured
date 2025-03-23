package day9;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


/*
http://localhost:3000/employees
http://localhost:3000/employees/1

http://localhost:3000/employees?last_name=King
http://localhost:3000/employees?first_name=Steven

http://localhost:3000/employees?gender=Female
http://localhost:3000/employees?gender=Male
*/

public class RequestAndResponseSpecification {

	RequestSpecification httpRequest;
	ResponseSpecification httpResponse;
	
	@BeforeClass
	void setup()
	{
		// Create request specification
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.setBaseUri("http://localhost:3000");
		reqBuilder.setBasePath("/employees");
		
		httpRequest = reqBuilder.build();
		
		// Create response specification
		ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
		resBuilder.expectStatusCode(200);
		resBuilder.expectHeader("Content-Type", equalTo("application/json"));
		
		httpResponse = resBuilder.build();
	}
	
	@Test (priority = 1)
	void getAllEmployees()
	{
		given()
			.spec(httpRequest)
		.when()
			.get()
		.then()
			.spec(httpResponse)
			.body("size()", greaterThan(0))
			.log().body();
	}
	
	@Test (priority = 2)
	void getMaleEmployees()
	{
		given()
			.spec(httpRequest)
			.queryParam("gender", "Male")
		.when()
			.get()
		.then()
			.spec(httpResponse)
			.body("gender", everyItem(equalTo("Male")))
			.log().body();
	}
	
	@Test (priority = 3)
	void getFemaleEmployees()
	{
		given()
			.spec(httpRequest)
			.queryParam("gender", "Female")
		.when()
			.get("http://localhost:3000/employees")
		.then()
			.spec(httpResponse)
			.body("gender", everyItem(equalTo("Female")))
			.log().body();
	}
	
	
	
	
	
	
	
	
	
}
