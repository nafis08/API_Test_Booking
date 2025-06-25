package NegativeTests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class NegativeTestForGetBookingDetails {
	
	@Test
	public void getBooking_NonExistingId_ShouldReturn404() {
		
		//Test with invalid ID
	    RestAssured.baseURI = "https://restful-booker.herokuapp.com";
	    int invalidBookingId = 999999;

	    int statusCode = given()
	        .when()
	        .get("/booking/" + invalidBookingId)
	        .getStatusCode();

	    Assert.assertEquals(statusCode, 404, "Expected 404 Not Found for non-existing booking ID.");
	}

	@Test
	public void getBooking_InvalidIdFormat_ShouldReturn404() {
		
		//Test with invalid ID
	    RestAssured.baseURI = "https://restful-booker.herokuapp.com";

	    String invalidId = "abc";

	    int statusCode = given()
	        .when()
	        .get("/booking/" + invalidId)
	        .getStatusCode();

	    Assert.assertEquals(statusCode, 404, "Expected 404 for invalid ID format.");
	}


}
