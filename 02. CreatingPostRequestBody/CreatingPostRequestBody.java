package day2;

import org.codehaus.groovy.transform.FieldASTTransformation;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;



import org.json.JSONObject;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

/*
 * Different ways to create POST request body
 * 1) using Hashmap
 * 2) using Org.JSON
 * 3) using POJO class
 * 4) using external json file data
 */

public class CreatingPostRequestBody {

		// 1) Creating post request body using HashMap
		//@Test
		void testPostUsingHashMap()
		{
			HashMap data = new HashMap();
			
			data.put("name", "Abdallah");
			data.put("location", "Egypt");
			data.put("phone", "0123456789");
			
			String coursesArr[] = {"C", "C++"};
			data.put("courses", coursesArr);
			
			given()
				.contentType("application/json")
				.body(data)
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
				.log().all();
		}
		
		// 2) Creating post request body using Org.JSON library
		//@Test
		void testPostUsingJsonLibrary()
		{
			JSONObject data = new JSONObject();
			
			data.put("name", "Abdallah");
			data.put("location", "Egypt");
			data.put("phone", "0123456789");
			
			String coursesArr[] = {"C", "C++"};
			data.put("courses", coursesArr);
			
			
			
			given()
				.contentType("application/json")
				.body(data.toString())
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
				.log().all();
		}

		// 3) Creating post request body using POJO
		//@Test
		void testPostUsingPOJO()
		{
			Pojo_PostRequest data = new Pojo_PostRequest();
			
			data.setName("Abdallah");
			data.setLocation("Egypt");
			data.setPhone("0123456789");
			
			String coursesArr[] = {"C", "C++"};
			data.setCourses(coursesArr);
			
			
			
			given()
				.contentType("application/json")
				.body(data)
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
				.log().all();
		}
		
		// 4) Creating post request body using External JSON File
		@Test
		void testPostUsingExternalJsonFile() throws FileNotFoundException
		{
			
			File f = new File(".\\body.json");
			
			FileReader fr = new FileReader(f);
			
			JSONTokener jt = new JSONTokener(fr);
			
			JSONObject data = new JSONObject(jt);
			
			given()
				.contentType("application/json")
				.body(data.toString())
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
				.log().all();
		}		
		
}
