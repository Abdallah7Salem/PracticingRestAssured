package day5;

import io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class parsingComplexJsonResponse {

	// Load the JSON Response File and return the Response as a JSON Object
	JSONObject getJSONResponse()
	{
		// 1) Access the file
		File myFile = new File(".\\src\\test\\resources\\complex.json");
		
		// 2) Read the file
		// Try-Catch Block to handle the exception "File Not Found"
		FileReader fileReader = null;
		try 
		{
			fileReader = new FileReader(myFile);
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 3) Convert the data into tokener format
		JSONTokener jsonTokener = new JSONTokener(fileReader);
		
		// 4) Create JSON Object to parse and validate data
		JSONObject jsonResponse = new JSONObject(jsonTokener);
		
		// 5) Return the Response as a JSON Object
		return jsonResponse;
		
	}
	
	@Test (priority = 1)
	void testUserDetailsValidation()
	{
		// Convert the response from JSON Object to JSON Path
		// toString() => JsonPath accepts only strings
		JsonPath jsonPath = new JsonPath(getJSONResponse().toString());
		
		
		// a) Verify Status
		String status = jsonPath.getString("status");
		assertThat(status, is("success"));
		
		// b) Validate user details
		int id = jsonPath.getInt("data.userDetails.id");
		String name = jsonPath.getString("data.userDetails.name");
		String email = jsonPath.getString("data.userDetails.email");
		
		assertThat(id, is(12345));
		assertThat(name, is("John Doe"));
		assertThat(email, is("john.doe@example.com"));
		
		// c) Validate home phone number
		String homePhoneType = jsonPath.getString("data.userDetails.phoneNumbers[0].type");
		String homePhone = jsonPath.getString("data.userDetails.phoneNumbers[0].number");
		
		assertThat(homePhoneType, is("home"));
		assertThat(homePhone, is("123-456-7890"));
		
		// d) Validate geo coordinates
		double latitude = jsonPath.getDouble("data.userDetails.address.geo.latitude");
		double longitude = jsonPath.getDouble("data.userDetails.address.geo.longitude");
		
		assertThat(latitude, is(39.7817));
		assertThat(longitude, is(-89.6501));
		
		// e) Validate preferences
		boolean notifications = jsonPath.getBoolean("data.userDetails.preferences.notifications");
		String theme = jsonPath.getString("data.userDetails.preferences.theme");
		
		assertThat(notifications, is(true));
		assertThat(theme, is("dark"));
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
