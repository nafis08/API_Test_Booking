package test.java;

import org.testng.annotations.Test;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SecurityTestsForGetToken {

	@Test
	public void createToken_SQLInjectionAttempt_ShouldFail() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		String response = given().header("Content-Type", "application/json")
				.body("{\"username\": \"admin' OR '1'='1\", \"password\": \"123\"}").when().post("auth").then()
				.statusCode(200).extract().asString();

		Assert.assertTrue(response.contains("reason"), "SQL injection should not bypass authentication");
	}

	@Test
	public void createToken_XSSInjection_ShouldBeHandled() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		String response = given().header("Content-Type", "application/json")
				.body("{\"username\": \"<script>alert('xss')</script>\", \"password\": \"123\"}").when().post("auth")
				.then().statusCode(200).extract().asString();

		Assert.assertTrue(response.contains("reason"), "XSS input should not be accepted");
	}

	@Test
	public void createToken_WithoutContentTypeHeader_ShouldFailGracefully() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		String response = given().body("{\"username\": \"admin\", \"password\": \"password123\"}").when().post("auth")
				.then().statusCode(200).extract().asString();

		Assert.assertTrue(response.contains("reason"), "Missing content-type");
	}

}
