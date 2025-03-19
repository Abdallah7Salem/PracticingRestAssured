package day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class GetUserTest {
	
	static final String BASE_URL = "https://gorest.co.in/public/v2/users";
	static final String BEARER_TOKEN = "861d1627df5e6facaf39710e116b25f902edcb82210745893b66fc085460b958";

	@Test (dependsOnMethods = {"day8.CreateUserTest.createUser"})
	void getUser(ITestContext context)
	{
		given()
			.headers("Authorization", "Bearer " + BEARER_TOKEN)
			.pathParam("id", (Integer)context.getAttribute("userId"))
		.when()
			.get(BASE_URL + "/{id}")
		.then()
			.statusCode(200)
			.log().body();
	}
	
}
