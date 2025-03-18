package day7;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;
import static org.hamcrest.MatcherAssert.assertThat;

public class SchemaValidations {

	// JsonSchema Validation
	//@Test (priority = 1)
	void testJsonSchema()
	{
		given()
		
		.when()
			.get("https://mocktarget.apigee.net/json")
		.then()
			// InClasspath: Fetch the JSON File from the src/test/resources directly
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchema.json"));
	}
	
	
	// Xml schema/XSD Validation
	@Test (priority = 2)
	void testXmlSchema()
	{
		given()
		
		.when()
			.get("https://mocktarget.apigee.net/xml")
		.then()
			// InClasspath: Fetch the JSON File from the src/test/resources directly
			.body(RestAssuredMatchers.matchesXsdInClasspath("xmlSchema.xsd"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
