package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {

	@Test(priority = 1)
	void testHeaders()
	{
		
		given()
			
		.when()
			.get("https://www.google.com/")
		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.header("Content-Encoding", "gzip")
			.header("Server", "gws");
		
	}
	
	@Test(priority = 2)
	void getHeaders()
	{
		
		Response res = given()	
						.when()
							.get("https://www.google.com/");
		
		// Get single header information
		String header_value = res.getHeader("Content-Type");
		System.out.println("The value of Content-type header is: " + header_value);
	
	
		// Get all headers information
		Headers my_headers = res.getHeaders();
		
		for(Header h : my_headers)
		{
			System.out.println(h.getName() + "   " + h.getValue());
		}
	}
	
}
