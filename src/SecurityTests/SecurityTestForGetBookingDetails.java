package SecurityTests;
import org.testng.annotations.Test;
import org.testng.Assert;
import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class SecurityTestForGetBookingDetails {
	
	@Test
	public void getBooking_XSSInjection_ShouldFail() {
		
		//XSSInjection
	    RestAssured.baseURI = "https://restful-booker.herokuapp.com";

	    String xssInput = "<script>alert('xss')</script>";

	    int statusCode = given()
	        .when()
	        .get("/booking/" + xssInput)
	        .getStatusCode();

	    Assert.assertTrue(statusCode == 400 || statusCode == 404,
	        "Expected 400 or 404 for XSS input. Actual: " + statusCode);
	}



}
