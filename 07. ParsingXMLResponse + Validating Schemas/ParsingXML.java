package day7;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.path.xml.XmlPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class ParsingXML {

	/*
	 * Test case to validate XML response data
	 * Validates:
	 * - HTTP status code
	 * - Content type
	 * - Specific XML element values
	 */
	
	//@Test (priority = 1)
	void testXMLResponse1()
	{
		given()
		
		.when()
			.get("https://mocktarget.apigee.net/xml")
		.then()
			.statusCode(200)
			// The 3 lines below do the same functionality
			.contentType("application/xml")
			.header("Content-Type", "application/xml; charset=utf-8")
			.header("Content-Type", equalTo("application/xml; charset=utf-8"))
			.log().body()
			.body("root.city", equalTo("San Jose"))
			.body("root.firstName", equalTo("John"))
			.body("root.lastName", equalTo("Doe"))
			.body("root.state", equalTo("CA"));
			
	}
	
	/*
	 * Test case to validate attributes in an XML response
	 * Validates:
	 * - HTTP status code
	 * - Content type
	 * - Specific XML attributes using '@' notation
	 */
	
	//@Test (priority = 2)
	void testXMLResponse2()
	{
		given()
		
		.when()
			.get("https://httpbin.org/xml")
		.then()
			.statusCode(200)
			.contentType("application/xml")
			.body("slideshow.@title", equalTo("Sample Slide Show"))
			.body("slideshow.@date", equalTo("Date of publication"))
			.body("slideshow.@author", equalTo("Yours Truly"))
			.log().body();
		
	}
	
	
	@Test (priority = 3)
	void testParsingXMLResponse()
	{
		Response response = 
		
		given()
		
		.when()
			.get("https://httpbin.org/xml")
		.then()
			.statusCode(200)
			.contentType("application/xml")
			.extract().response();
		
		// Extract the XML Path to parse the XML data
		XmlPath xmlPath = new XmlPath(response.asString());
		
		// Capturing titles of the slides in the response
		List<String> slideTitles = xmlPath.getList("slideshow.slide.title");
		
		// Counting slides
		assertThat(slideTitles.size(), is(2));
		
		// Validate slide titles
		assertThat(slideTitles.get(0), is("Wake up to WonderWidgets!"));	// Specific title
		assertThat(slideTitles.get(1), is("Overview"));
		
		assertThat(slideTitles, hasItems("Wake up to WonderWidgets!", "Overview"));	// Multiple titles
		
		
		// Items
		
		// Validate number of items
		List<String> slideItems = xmlPath.getList("slideshow.slide.item");
		System.out.println("Number of Items: " + slideItems.size());
		assertThat(slideItems.size(), is(3));
		
		// Validate items data
		assertThat(slideItems.get(0), is("WonderWidgets"));
		assertThat(slideItems.get(2), is("buys"));
		
		assertThat(slideItems, hasItems("WonderWidgets", "buys"));
		
		// Check presence of an item in the response
		boolean status = false;
		for (String item: slideItems)
		{
			if (item.equals("WonderWidgets"))
			{
				status = true;
				break;
			}
		}
		assertThat(status, is(true));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
