package NegativeTests;
import org.testng.annotations.Test;
import org.testng.Assert;
import io.restassured.RestAssured;
import utils.payload;

import static io.restassured.RestAssured.given;

public class NegativeTestForBookingCreation {
	
	@Test
	public void createBooking_MissingFirstName_ShouldFail() {
	    //Booking creation should fail due to incomplete body
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

	    int statusCode = given()
	        .contentType("application/json")
	        .body(payload.incompleteBody())
	    .when()
	        .post("/booking").getStatusCode();
	    
	    
	    Assert.assertTrue(statusCode >= 400, "Expected client/server error but got: " + statusCode);
	}
	
	@Test
	public void createBooking_InvalidPriceType_ShouldFail() {
		
		//Should fail due to invalid data type
	    RestAssured.baseURI = "https://restful-booker.herokuapp.com";

	    String payload = "{ \"firstname\": \"John\", \"lastname\": \"Doe\", \"totalprice\": \"abc\", \"depositpaid\": true, \"bookingdates\": { \"checkin\": \"2023-01-01\", \"checkout\": \"2023-01-10\" } }";

	    int statusCode = given()
	            .contentType("application/json")
	            .body(payload)
	        .when()
	            .post("/booking")
	        .getStatusCode();

	        Assert.assertTrue(statusCode >= 400, "Expected failure due to invalid data type. Got: " + statusCode);
	}
	
	@Test
	public void createBooking_EmptyBody_ShouldFail() {
		
		//Should fail due to empty Json body
	    RestAssured.baseURI = "https://restful-booker.herokuapp.com";

	    int statusCode = given()
	            .contentType("application/json")
	            .body("")
	        .when()
	            .post("/booking")
	        .getStatusCode();

	        Assert.assertTrue(statusCode >= 400, "Expected failure due to empty request body. Got: " + statusCode);
	}
	
	@Test
	public void createBooking_InvalidJsonFormat_ShouldFail() {
		
		//Should fail due to invalid Json
	    RestAssured.baseURI = "https://restful-booker.herokuapp.com";

	    String invalidJson = "{ \"firstname\": \"Rick\", \"lastname\": \"Sanchez\" ";

	    int statusCode = given()
	        .contentType("application/json")
	        .body(invalidJson)
	    .when()
	        .post("/booking")
	    .getStatusCode();

	    Assert.assertTrue(statusCode >= 400, "Expected failure due to malformed JSON. Got: " + statusCode);
	}





}
