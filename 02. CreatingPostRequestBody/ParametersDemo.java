package day2;

import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

public class ParametersDemo {

	// @Test
	void pathParams()
	{
		
		given()
			.pathParam("country", "Egypt")
		.when()
			.get("https://restcountries.com/v2/name/{country}")		// Path parameter
		.then()
			.statusCode(200)
			.log().body();
	}
	
	@Test
	void queryParams()
	{
		given()
			.queryParam("page", 2)
			.queryParam("id", 5)
		.when()
			.get("https://reqres.in/api/users")
		.then()
			.statusCode(200)
			.log().body();
		
		
		
	}
	
}
