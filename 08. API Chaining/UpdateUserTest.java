package day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import static io.restassured.RestAssured.*;
import org.json.JSONObject;
public class UpdateUserTest {
	
	static final String BASE_URL = "https://gorest.co.in/public/v2/users";
	static final String BEARER_TOKEN = "861d1627df5e6facaf39710e116b25f902edcb82210745893b66fc085460b958";

	@Test (dependsOnMethods = {"day8.GetUserTest.getUser"})
	void updateUser(ITestContext context)
	{
		JSONObject requestData = new JSONObject();
		
		Faker faker = new Faker();
		
		requestData.put("name", faker.name().fullName());
		requestData.put("gender", "Male");
		requestData.put("email", faker.internet().emailAddress());
		requestData.put("status", "active");
		
				
		given()
			.headers("Authorization", "Bearer " + BEARER_TOKEN)
			.contentType("application/json")
			.body(requestData.toString())
			.pathParam("id", (Integer)context.getAttribute("userId"))
		.when()
			.put(BASE_URL + "/{id}")
		.then()
			.statusCode(200)
			.log().body();
		
	}
	
}
