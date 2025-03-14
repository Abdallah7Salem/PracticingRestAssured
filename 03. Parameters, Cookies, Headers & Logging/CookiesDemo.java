package day3;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Cookie;
import io.restassured.response.Response;


public class CookiesDemo {

	@Test(priority = 1)
	void testCookies()
	{
		
		Response res = given()
			
		.when()
			.get("https://www.google.com/")
		.then()
			.log().cookies()
			.statusCode(200)
			.cookie("AEC", notNullValue())
			.extract().response();
		
		// Extract a specific cookie
		String cookieValue = res.getCookie("AEC");
		System.out.println("Value of 'AEC' Cookie: " + cookieValue);
		
	}
	
	@Test(priority = 2)
	void getCookiesInfo()
	{
		
		Response res = given()
			
		.when()
			.get("https://www.google.com/");
		
		
		// Get single cookie information
		String cookie_value = res.getCookie("AEC");
		System.out.println("Value of cookie is ====> " + cookie_value);
	
		// Get all cookies information
		Map<String, String> cookies_values = res.getCookies();
		
		System.out.println(cookies_values.keySet());
		
		for(String key : cookies_values.keySet())
		{
			String current_cookie_value = res.getCookie(key);
			System.out.println(key + "     " + current_cookie_value);
		}
		
		
		// Get detailed information about cookie
		Cookie cookie_info = res.getDetailedCookie("AEC");
		
		// Verify the cookie expiration date
		System.out.println(cookie_info.hasExpiryDate());
		System.out.println(cookie_info.getExpiryDate());
		
		// Verify the cookie value
		System.out.println(cookie_info.hasValue());
		System.out.println(cookie_info.getValue());
		
		// Verify the cookie security
		System.out.println(cookie_info.isSecured());
		
	}
	 
}
