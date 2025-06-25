package NegativeTests;

import org.testng.annotations.Test;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTestsForCreateToken {

	@Test
	public void createToken_InvalidUsername_ShouldReturn403() {

		// Wrong userName
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		String response = given().header("Content-Type", "application/json")
				.body("{\"username\": \"wrongUser\", \"password\": \"password123\"}").when().post("auth").then()
				.statusCode(200).extract().asString();

		Assert.assertTrue(response.contains("reason"), "Error response should contain a reason for failure");
	}

	@Test
	public void createToken_InvalidPassword_ShouldFail() {

		// Wrong password
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		String response = given().header("Content-Type", "application/json")
				.body("{\"username\": \"admin\", \"password\": \"wrongPass\"}").when().post("auth").then()
				.statusCode(200).extract().asString();

		Assert.assertTrue(response.contains("reason"), "Should contain error reason when credentials are wrong");
	}

	@Test
	public void createToken_MissingPassword_ShouldReturnError() {

		// Missing parameter
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		String response = given().header("Content-Type", "application/json").body("{\"username\": \"admin\"}").when()
				.post("auth").then().statusCode(200).extract().asString();

		Assert.assertTrue(response.contains("reason"), "Should show error reason for missing password");
	}

}
