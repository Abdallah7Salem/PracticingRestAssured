package day9;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class SerializationAndDeserializationExample {

	String studentId;
	
	@Test (priority = 1)
	public void testSerialization()
	{
		String courses[] = {"Selenium", "Playwright", "Appium"};
		Student student = new Student("Abdallah", "Cairo", "123456789", courses);
		
		studentId = given()
			.contentType("application/json")
			.body(student)
		.when()
			.post("http://localhost:3000/students")
		.then()
		.statusCode(201)
		.log().body()
		.extract().response().jsonPath().getString("id");
		
	}
	
	@Test (priority = 2, dependsOnMethods = {"testSerialization"})
	public void testDeserialization()
	{
		Response response = 
		given()
			.pathParam("id", studentId)
		.when()
			.get("http://localhost:3000/students/{id}")
		.then()
			.statusCode(200)
			.extract().response();
		
		// Extract ID separately from JSON response
		// Why? ID is not part of the POJO Class, it is created in the response
		String extractedId = response.jsonPath().getString("id");
		
		// Deserialization
		// Convert JSON response to Student Object
		Student studentRes = response.as(Student.class);
				
		assertThat(studentRes.getName(), is("Abdallah"));
		assertThat(studentRes.getLocation(), is("Cairo"));
		assertThat(studentRes.getPhone(), is("123456789"));
		assertThat(studentRes.getCourses()[0], is("Selenium"));
		
		// Printing student object details
		System.out.println("Student details: " + studentRes + extractedId);
	
	}
	
	@Test (priority = 3, dependsOnMethods = "testSerialization")
	public void deleteStudent()
	{
		given()
			.pathParam("id", studentId)
		.when()
			.delete("http://localhost:3000/students/{id}")
		.then()
			.statusCode(200);
		
	}
	
	
}
