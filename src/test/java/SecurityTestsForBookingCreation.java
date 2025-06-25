package test.java;
import org.testng.annotations.Test;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class SecurityTestsForBookingCreation {
	
	@Test
	public void createBooking_SQLInjectionAttempt_ShouldBeHandledSafely() {
		
		//Server should not be affected
	    RestAssured.baseURI = "https://restful-booker.herokuapp.com";

	    String payload = "{ \"firstname\": \"Robert'); DROP TABLE booking;--\", \"lastname\": \"Hacker\", \"totalprice\": 111, \"depositpaid\": true, \"bookingdates\": { \"checkin\": \"2023-01-01\", \"checkout\": \"2023-01-10\" } }";

	    int statusCode = given()
	        .contentType("application/json")
	        .body(payload)
	    .when()
	        .post("/booking")
	    .getStatusCode();

	    Assert.assertTrue(statusCode == 200 || statusCode >= 400, "Server should not crash from SQL-like input. Got: " + statusCode);

	}
	
	@Test
	public void createBooking_XSSAttempt_ShouldNotExecuteScript() {
		
		//Script should not be executed
	    RestAssured.baseURI = "https://restful-booker.herokuapp.com";

	    String payload = "{ \"firstname\": \"<script>alert('XSS')</script>\", \"lastname\": \"Tester\", \"totalprice\": 100, \"depositpaid\": true, \"bookingdates\": { \"checkin\": \"2023-01-01\", \"checkout\": \"2023-01-10\" } }";

	    String response = given()
	        .contentType("application/json")
	        .body(payload)
	    .when()
	        .post("/booking")
	    .then()
	        .statusCode(200)
	        .extract().asString();

	    JsonPath js = new JsonPath(response);
	    String firstname = js.getString("booking.firstname");

	    Assert.assertFalse(firstname.contains("<script>"), "Wrong type of JSON");
	}


}
