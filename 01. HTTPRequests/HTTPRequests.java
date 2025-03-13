package day1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;




public class HTTPRequests {

	int userId;
	
	@Test (priority = 1)
	void getUsers()
	{
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
		
		.then()
			.statusCode(200)
			.body("page", equalTo(2))
			.time(lessThan(2000L))
			.log().all();
	}
	
	@Test (priority = 2)
	void createUser()
	{
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "Abdallah");
		data.put("job", "Software Tester");
		
		userId=given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.time(lessThan(2000L))
			.body("name", equalTo("Abdallah"))
			.body("job", equalTo("Software Tester"))
			.body(containsString("id"))
			.log().all()
			.extract().jsonPath().getInt("id");
	}
	
	@Test (priority = 3, dependsOnMethods = {"createUser"})
	void updateUser()
	{
		HashMap<String, String> data = new HashMap<String, String>();
		data.put("name", "Abdallah Qandil");
		data.put("job", "Software Testing Engineer");
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.put("https://reqres.in/api/users/" + userId)
		.then()
			.statusCode(200)
			.time(lessThan(2000L))
			.body("name", equalTo("Abdallah Qandil"))
			.body("job", equalTo("Software Testing Engineer"))
			.log().all();
	}
	
	@Test (priority = 4, dependsOnMethods = {"createUser", "updateUser"})
	void deleteUser()
	{
		given()
		
		.when()
			.delete("https://reqres.in/api/users" + userId)
		.then()
			.statusCode(204)
			.time(lessThan(2000L))
			.body(emptyOrNullString())
			.log().all();
	}	
	
	
	
}
