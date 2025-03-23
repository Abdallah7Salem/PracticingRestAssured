package day10;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class SubmitOrder {

	private static final String AUTH_TOKEN = "Bearer 10db885400fd6037021d68bb14548b24be7b9a5ccdc2673eb1071a39334c622a";
	private static final String BASE_URL = "https://simple-books-api.glitch.me/orders";
	
	@Test
	void testSubmitAndDeleteOrder()
	{
		// Submitting Order
		JSONObject requestBody = new JSONObject();
		requestBody.put("bookId", 1);
		requestBody.put("customerName", "Abdallah");
		
		String orderId = 
		given()
			.contentType("application/json")
			.header("Authorization", AUTH_TOKEN)
			.body(requestBody.toString())
		.when()
			.post(BASE_URL)
		.then()
			.statusCode(201)
			.log().body()
			.extract().jsonPath().getString("orderId");
		// End of Submitting Order
		
		// Deleting Order
		given()
			.header("Authorization", AUTH_TOKEN)
			.pathParam("orderId", orderId)
		.when()
			.delete(BASE_URL + "/{orderId}")
		.then()
			.statusCode(204);
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
