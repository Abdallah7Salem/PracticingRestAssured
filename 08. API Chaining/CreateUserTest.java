package day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import static io.restassured.RestAssured.*;
import org.json.JSONObject;

public class CreateUserTest {

	static final String BASE_URL = "https://gorest.co.in/public/v2/users";
	static final String BEARER_TOKEN = "861d1627df5e6facaf39710e116b25f902edcb82210745893b66fc085460b958";
	
	int userId;
	
	Faker faker = new Faker();
	
	@Test
	void createUser(ITestContext context)
	{
		JSONObject requestData = new JSONObject();
		requestData.put("name", faker.name().fullName());
		requestData.put("gender", "Male");
		requestData.put("email", faker.internet().emailAddress());
		requestData.put("status", "inactive");
		
		userId = 
				
		given()
			.headers("Authorization", "Bearer " + BEARER_TOKEN)
			.contentType("application/json")
			.body(requestData.toString())
		.when()
			.post(BASE_URL)
		.then()
			.statusCode(201)
			.log().body()
			.extract().response().jsonPath().getInt("id");
		
		// To make the userId variable global and used between different classes
		context.setAttribute("userId", userId);
		
	}
	
}
