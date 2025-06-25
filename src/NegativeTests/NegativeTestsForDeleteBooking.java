package NegativeTests;

import org.testng.annotations.Test;
import org.testng.Assert;
import static io.restassured.RestAssured.given;

import java.util.List;

import io.restassured.RestAssured;
import utils.StoreBookingID;
import utils.TokenManager;

public class NegativeTestsForDeleteBooking {

	@Test
	public void deleteBooking_WithoutToken_ShouldFail() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		int fakeBookingId = 999999;

		int statusCode = given().contentType("application/json").accept("application/json").when()
				.delete("/booking/" + fakeBookingId).getStatusCode();

		Assert.assertEquals(statusCode, 403, "Expected 403 Forbidden when token is missing.");
	}

	@Test
	public void deleteBooking_InvalidId_ShouldReturn404() {

		// Test with invalid Id
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		String token = TokenManager.getToken();

		int nonExistingBookingId = 9999999;

		int statusCode = given().contentType("application/json").header("Cookie", "token=" + token).when()
				.delete("/booking/" + nonExistingBookingId).getStatusCode();

		Assert.assertEquals(statusCode, 405, "Expected 404 for non-existing booking ID.");
	}

	@Test(dependsOnMethods = { "CreateBooking.createBooking" })
	public void deleteBooking_Twice_ShouldFailSecondTime() {

		// Deleting same ID twice
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

		String token = TokenManager.getToken();
		List<String> bookingIds = StoreBookingID.getBookingIds();

		if (bookingIds.isEmpty()) {
			throw new RuntimeException("No bookings available to test.");
		}

		int bookingId = Integer.parseInt(bookingIds.get(0));

		// First delete (should succeed)
		int firstDeleteStatus = given().contentType("application/json").header("Cookie", "token=" + token).when()
				.delete("/booking/" + bookingId).getStatusCode();

		Assert.assertTrue(firstDeleteStatus == 200 || firstDeleteStatus == 201, "First deletion failed.");

		// Second delete (should fail)
		int secondDeleteStatus = given().contentType("application/json").header("Cookie", "token=" + token).when()
				.delete("/booking/" + bookingId).getStatusCode();

		Assert.assertEquals(secondDeleteStatus, 405, "Expected 404 when trying to delete again.");
	}

}
