package day3;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;


public class CookiesDemo {

	@Test(priority = 1)
	void testCookies()
	{
		
		given()
			
		.when()
			.get("https://www.google.com/")
		.then()
			// The cookie value changes with every request
			// So, if the test failed, it's working fine.
			.cookie("AEC", "AVcja2c26HL_hzcenmaIB2C3jUl13zVzLWdlu9-1lYoA8Kj3iOB9ckvWBg")
			.log().all();
		
		
		
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
	}
	
}
