package day8;

import org.testng.annotations.Test;
import com.github.javafaker.Faker;

public class GenerateTestDataUsingFakerLibrary {

	@Test
	void fakeDataGenerator()
	{
		Faker faker = new Faker();
		
		String fullName = faker.name().fullName();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		
		//String email = faker.internet().emailAddress();
		String email = faker.internet().safeEmailAddress();
		
		String password = faker.internet().password(5, 8);	// 5 -> minimum, 8 -> maximum
		
		String phoneNumber = faker.phoneNumber().cellPhone();
		
		System.out.println("Full Name: " + fullName);
		System.out.println("First Name: " + firstName);
		System.out.println("Last Name: " + lastName);
		System.out.println("Email: " + email);
		System.out.println("Password: : " + password);
		System.out.println("Phone Number: " + phoneNumber);
	}
	
	
	
}
