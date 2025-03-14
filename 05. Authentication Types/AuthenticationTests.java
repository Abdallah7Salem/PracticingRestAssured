package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class AuthenticationTests {

	// 1. Basic Authentication
	//@Test
	void verifyBasicAuth()
	{
		given()
			.auth().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().body();
	}
	
	// 2. Basic Preemptive Authentication (Supported only through RestAssured)
	// Preemptive => encrypting the user name and password before sending them to the server
	//@Test
	void verifyBasicPreemptiveAuth()
	{
		given()
			.auth().preemptive().basic("postman", "password ")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().body();
	}
	
	
	// 3. Digest Authentication
	//@Test
	void verifyDigestAuth()
	{
		given()
			.auth().digest("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().body();
	}
	
	// 4. Bearer Token Authentication
	//@Test
	void verifyBearerTokenAuth()
	{
		String bearerToken = "PLACE YOUR BEARER TOKEN HERE";
		
		given()
			// Bearer Token is sent within the request Header
			.header("Authorization", "Bearer " + bearerToken)
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.body("authenticated", equalTo(true))
			.log().body();
	}
	
	
	// 5. API Key Authentication
	@Test
	void veriftyApiKeyAuth()
	{
		given()
			.queryParam("q", "Cairo")
			.queryParam("appid", "PLACE YOUR API KEY HERE")
		.when()
			.get("https://api.openweathermap.org/data/2.5/weather")
		.then()
			.statusCode(200)
			.log().body();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
