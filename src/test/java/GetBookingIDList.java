package test.java;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import POJO.BookingId;
import io.restassured.RestAssured;

import java.util.Arrays;
import java.util.List;
import org.testng.Assert;

public class GetBookingIDList {

	@Test
	public void getBookingIDs() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		// Parse JSON array to Java array
		BookingId[] bookingIdArray = given().log().all().queryParam("checkin", "2025-01-01").when().get("booking")
				.as(BookingId[].class); // Deserialize as an array

		// Convert to List for easy iteration
		List<BookingId> bookingIdList = Arrays.asList(bookingIdArray);

		// Print all booking IDs
		for (BookingId booking : bookingIdList) {
			System.out.println("Booking ID: " + booking.getBookingid());
		}

		Assert.assertNotNull(bookingIdArray, "Booking ID array is not null");

	}
}
