package day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/*
 * 1) From the application (Manual process)
 * 		- Client ID
 * 		- Client Secret
 * 
 * 2) Send post request for getting token
 * POST https://api.imgur.com/oauth2/token
 * 		- Client ID
 * 		- Client Secret
 * 		- tokenURL
 * 		- RedirectURL
 * 		- Grant Type
 * 		- Authorization Code
 * 
 */


public class OAuth2AuthenticationDemo {

	@Test
	void verifyOAuth2Authentication()
	{
		String clientID = "PLACE CLIENT ID HERE";
		String clientSecret = "PLACE CLIENT SECRET HERE";
		String tokenURL = "https://api.imgur.com/oauth2/token";
		String redirectURL = "https://www.getpostman.com/oauth2/callback";
		String grantType = "authorization_code";
		String authorizationCode = "PLACE AUTHORIZATION CODE HERE";
		
		String token = given()
			.formParam("client_id", clientID)
			.formParam("client_secret", clientSecret)
			.formParam("redirect_uri", redirectURL)
			.formParam("grant_type", grantType)
			.formParam("code", authorizationCode)
		.when()
			.post(tokenURL)
		.then()
			.statusCode(200)
			.extract().jsonPath().getString("access_token");
		
		
		given()
			.auth().oauth2(token)
		.when()
			.get("https://api.imgur.com/3/account/me/images")
		.then()
			.statusCode(200)
			.log().body();
		
	}
	
	
	
	
	
	
	
	
	
}
