package day6;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class ParsingJSONArray_EmployeesAPI {

	@Test
	void testJsonResponseBody()
	{
		
		
		ResponseBody responseBody = 
				
		given()
		
		.when()
			.get("http://localhost:3000/employees")
		.then()
			.statusCode(200)
			.extract().response().body();
		
		
		JsonPath jsonPath = new JsonPath(responseBody.asString());
		
		// Get the size of the JSON Array
		int employeeCount = jsonPath.getInt("size()"); 
		
		// Print all the details of the employee
		for (int i = 0; i < employeeCount; i++)
		{
			/*
			 * If the array doesn't have a name, it's anonymous array
			 * indexing brackets will be used without a preceding name
			 */
			String firstName = jsonPath.getString("["+i+"].first_name");
			String lastName = jsonPath.getString("["+i+"].last_name");
			String email = jsonPath.getString("["+i+"].email");
			String gender = jsonPath.getString("["+i+"].gender");
			
			System.out.println(firstName + " " + lastName + " " + email + " " + gender);
		}
		
		// Search for an employee name "Steve" in the list
		boolean status = false;
		
		for (int i = 0; i < employeeCount; i++)
		{
			String firstName = jsonPath.getString("["+i+"].first_name");
			
			if(firstName.equals("Steve"))
			{
				status = true;
				break;
			}
		}
		
		assertThat(status, is(true));	// Steve exists in the list or not
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
