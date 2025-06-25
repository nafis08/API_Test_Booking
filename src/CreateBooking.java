import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utils.*;

public class CreateBooking {
	
	@Test(dataProvider="BookingData", dataProviderClass = payload.class)
	public void createBooking(String firstName, String lastName, String additionalNeeds) {
        
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        String response = given()
        	    .contentType("application/json")
        	    .accept("application/json")
        	    .body(payload.createBooking(firstName, lastName, additionalNeeds))
        	.when()
        	    .post("booking")
        	.then()
        	    .statusCode(200)
            .extract().response().asString();

        //System.out.println("Response:\n" + response);

        JsonPath js = ReUsableMethods.rawToJson(response);
        String bookingid = js.getString("bookingid");
        System.out.println("Booking Id: " + bookingid);
        
        Assert.assertNotEquals(bookingid, 0, "Booking ID should not be zero");
        
        StoreBookingID.addBookingId(bookingid);

    }

}



