package day10;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.util.Map;

import org.json.JSONObject;
import org.testng.annotations.Test;


public class DataDrivenTesting {

	private static final String AUTH_TOKEN = "Bearer 10db885400fd6037021d68bb14548b24be7b9a5ccdc2673eb1071a39334c622a";
	private static final String BASE_URL = "https://simple-books-api.glitch.me/orders";
	
	//@Test (dataProvider = "excelDataProvider", dataProviderClass = DataProviders.class)
	public void testWithExcelData(String bookId, String customerName)
	{
		testSubmitAndDeleteOrder(bookId, customerName);
	}
	
	//@Test (dataProvider = "jsonDataProvider", dataProviderClass = DataProviders.class)
	public void testWithJsonData(Map<String, String> data)
	{
		testSubmitAndDeleteOrder(data.get("BookID"), data.get("CustomerName"));
	}
	
	@Test (dataProvider = "csvDataProvider", dataProviderClass = DataProviders.class)
	public void testWithCSVData(String bookId, String customerName)
	{
		testSubmitAndDeleteOrder(bookId, customerName);
	}
	
	public void testSubmitAndDeleteOrder(String bookId, String customerName)
	{
		// Submitting Order
		JSONObject requestBody = new JSONObject();
		requestBody.put("bookId", Integer.parseInt(bookId));
		requestBody.put("customerName", customerName);
		
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
