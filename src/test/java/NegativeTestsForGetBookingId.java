package test.java;
import org.testng.annotations.Test;

import org.testng.Assert;

import POJO.BookingId;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;


public class NegativeTestsForGetBookingId {
	
	@Test
	public void getBookingIds_InvalidCheckinFormat_ShouldReturnEmptyList() {
		
		//Invalid query param
	    RestAssured.baseURI = "https://restful-booker.herokuapp.com";

	    BookingId[] bookingIdArray = given()
	        .queryParam("checkin", "01-01-2025") // Invalid format
	        .when()
	        .get("booking")
	        .then()
	        .statusCode(200) 
	        .extract().as(BookingId[].class);

	    Assert.assertEquals(bookingIdArray.length, 0, "Expected empty list for invalid date format.");
	}

}
