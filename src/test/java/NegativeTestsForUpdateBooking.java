package test.java;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import utils.StoreBookingID;
import utils.TokenManager;
import utils.payload;

public class NegativeTestsForUpdateBooking {

	@Test
	public void partialUpdateWithoutToken() {
		
		//Test without token
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		List<String> bookingId = StoreBookingID.getBookingIds();

		given().contentType("application/json")
		.accept("application/json")
		.body(payload.partialDetails())
		.when().patch("/booking/" + bookingId)
		.then().statusCode(403); // Forbidden due to missing token
	}

	@Test
	public void partialUpdateWithInvalidJson() {
		
		//Test with invalid JSON
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		String token = TokenManager.getToken();

		List<String> bookingId = StoreBookingID.getBookingIds();

		given().contentType("application/json")
		.accept("application/json")
		.header("Cookie", "token=" + token)
		.body("{invalidJson}") // Invalid JSON
		.when().patch("/booking/" + bookingId)
		.then().statusCode(400); // Bad Request
	}

}
