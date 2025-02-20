package day4;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class parsingJSONResponseData {

	//@Test (priority = 1)
	void testJsonResponse()
	{
		/*
		// Approach 1
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store")
		.then()
			.statusCode(200)
			.header("Content-Type", "application/json")
			.body("book[3].title", equalTo("The Lord of the Rings"));
		*/
		
		
		// Approach 2
		
		Response res = given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store");
		
		// Validate the status code
		Assert.assertEquals(res.getStatusCode(), 200);
		
		// Validate the response header
		Assert.assertEquals(res.header("Content-Type"), "application/json");
		
		// Validate the response body (title of the third book)
		String bookName = res.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookName, "The Lord of the Rings");
		
		// 
		
	}
	
	@Test (priority = 2)
	void testJsonResponseBodyData()
	{
		Response res = 
		
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store");
			
		// JSONObject Class (pre-defined)
		// First, convert the response to JSONObject type
		JSONObject jo = new JSONObject(res.asString());
		
		/*
		// Print all titles of books
		for(int i = 0; i < jo.getJSONArray("book").length(); i++)	
		{
			String bookTitle =  jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.out.println(bookTitle);
		}
		*/
	
		// search for title of the book in json
		boolean status = false;
		
		for (int i = 0; i < jo.getJSONArray("book").length(); i++)	
		{
			String bookTitle =  jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			
			if (bookTitle.equals("The Lord of the Rings"))
			{
				status = true;
				break;
			}
		}
		
		Assert.assertEquals(status, true);
	
		// Validate total price of books
		double totalPrice = 0;
		
		for (int i = 0; i < jo.getJSONArray("book").length(); i++)	
		{
			String price =  jo.getJSONArray("book").getJSONObject(i).get("price").toString();	
			totalPrice += Double.parseDouble(price);
			
		}
		
		System.out.println("Total price of books is: " + totalPrice);
		
		Assert.assertEquals(totalPrice, 53.92);
	
	}	
}
