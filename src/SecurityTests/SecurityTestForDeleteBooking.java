package SecurityTests;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.RestAssured;
import utils.TokenManager;

public class SecurityTestForDeleteBooking {
	
	@Test
	public void deleteBooking_InvalidToken_ShouldBeForbidden() {
		
		//Test with invalid token
	    RestAssured.baseURI = "https://restful-booker.herokuapp.com";

	    int fakeBookingId = 101;

	    int statusCode = given()
	        .contentType("application/json")
	        .header("Cookie", "token=INVALIDTOKEN123")
	    .when()
	        .delete("/booking/" + fakeBookingId)
	    .getStatusCode();

	    Assert.assertEquals(statusCode, 403, "Expected 403 for invalid token.");
	}
	
	
	@Test
	public void deleteBooking_XSSAttempt_ShouldNotSucceed() {
		
		//Test with invalid path param
	    RestAssured.baseURI = "https://restful-booker.herokuapp.com";

	    String token = TokenManager.getToken();
	    String xssInput = "<script>alert('xss')</script>";

	    int statusCode = given()
	        .contentType("application/json")
	        .header("Cookie", "token=" + token)
	    .when()
	        .delete("/booking/" + xssInput)
	    .getStatusCode();

	    Assert.assertTrue(statusCode == 400 || statusCode == 404, "Expected failure for invalid ID input.");
	}




}
