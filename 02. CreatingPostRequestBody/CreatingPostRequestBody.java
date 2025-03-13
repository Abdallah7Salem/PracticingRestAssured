package day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Different ways to create POST request body
 * 1) using Hashmap
 * 2) using Org.JSON
 * 3) using POJO class
 * 4) using external json file data
 */

public class CreatingPostRequestBody {

		String studentId;
	
		// 1) Creating post request body using HashMap
		// @Test
		void testPostUsingHashMap()
		{
			HashMap<String, Object> requestBody = new HashMap<>();
			
			requestBody.put("name", "Abdallah");
			requestBody.put("location", "Egypt");
			requestBody.put("phone", "0123456789");
			
			String coursesArr[] = {"C", "C++"};
			requestBody.put("courses", coursesArr);
			
			studentId = given()
				.contentType("application/json")
				.body(requestBody)
			.when()
				.post("http://localhost:3000/students")
			.then()
				.statusCode(201)
				.body("name", equalTo("Abdallah"))
				.body("location", equalTo("Egypt"))
				.body("phone", equalTo("0123456789"))
				.body("courses[0]", equalTo("C"))
				.body("courses[1]", equalTo("C++"))
				.header("Content-Type", "application/json")
				.log().all()
				.extract().jsonPath().getString("id");
		}
		
		// 2) Creating post request body using Org.JSON library
		// @Test
		void createStudentUsingJsonLibrary()
		{
			JSONObject requestBody = new JSONObject();
			
			requestBody.put("name", "Abdallah");
			requestBody.put("location", "Egypt");
			requestBody.put("phone", "0123456789");
			
			String coursesArr[] = {"C", "C++"};
			requestBody.put("courses", coursesArr);
			
			
			
			studentId = given()
				.contentType("application/json")
				.body(requestBody.toString())	// Convert the request body from JSON Object to String Format
			.when()
				.post("http://localhost:3000/students")
			.then()
				.statusCode(201)
				.body("name", equalTo("Abdallah"))
				.body("location", equalTo("Egypt"))
				.body("phone", equalTo("0123456789"))
				.body("courses[0]", equalTo("C"))
				.body("courses[1]", equalTo("C++"))
				.header("Content-Type", "application/json")
				.log().all()
				.extract().jsonPath().getString("id");
		}

		// 3) Creating post request body using POJO
		//@Test
		void createStudentUsingPojoClass()
		{
			Pojo_PostRequest requestBody = new Pojo_PostRequest();
			
			requestBody.setName("Abdallah");
			requestBody.setLocation("Egypt");
			requestBody.setPhone("0123456789");
			
			String coursesArr[] = {"C", "C++"};
			requestBody.setCourses(coursesArr);
			
			
			
			given()
				.contentType("application/json")
				.body(requestBody)
			.when()
				.post("http://localhost:3000/students")
			.then()
				.statusCode(201)
				.body("name", equalTo(requestBody.getName()))
				.body("location", equalTo(requestBody.getLocation()))
				.body("phone", equalTo(requestBody.getPhone()))
				.body("courses[0]", equalTo(requestBody.getCourses()[0]))
				.body("courses[1]", equalTo(requestBody.getCourses()[1]))
				.header("Content-Type", "application/json")
				.log().all();
		}
		
		// 4) Creating post request body using External JSON File
		//@Test
		void createStudentUsingExternalFile() throws FileNotFoundException
		{
			
			File f = new File(".\\body.json");
			
			// Read the data from the file
			FileReader fileReader = new FileReader(f);
			
			// Text file can be read directly, but JSON files need to be read as tokens
			JSONTokener jsonTokener = new JSONTokener(fileReader);
			
			// Convert json tokener into json object
			JSONObject requestBody = new JSONObject(jsonTokener);
			
			studentId = given()
				.contentType("application/json")
				.body(requestBody.toString())	// Convert the JSON object to string format
			.when()
				.post("http://localhost:3000/students")
			.then()
				.statusCode(201)
				.body("name", equalTo("Abdallah"))
				.body("location", equalTo("Egypt"))
				.body("phone", equalTo("0123456789"))
				.body("courses[0]", equalTo("C"))
				.body("courses[1]", equalTo("C++"))
				.header("Content-Type", "application/json")
				.log().all()
				.extract().jsonPath().getString("id");
		}		
		
		
		@AfterMethod
		void deleteStudentRecord()
		{
			given()
			
			.when()
				.delete("http://localhost:3000/students/" + studentId)
			.then()
				.statusCode(200);
		}
		
}
