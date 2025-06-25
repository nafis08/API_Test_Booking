package test.java;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;


import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utils.StoreBookingID;
import utils.TokenManager;
import utils.payload;

public class DeleteBooking {
	
	@Test(dependsOnMethods = {"test.java.GetToken.createToken","test.java.CreateBooking.createBooking"})
	public void deleteBooking() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        String token = TokenManager.getToken();
        System.out.println("Token: " + token);

        List<String> bookingIds = StoreBookingID.getBookingIds();

        if (bookingIds.isEmpty()) {
            System.out.println("No booking IDs found to update.");
            return;
        }

        for (String id : bookingIds) {
            int bookingId = Integer.parseInt(id);

            String response = given()
                    .contentType("application/json")
                    .accept("application/json")
                    .header("Cookie", "token=" + token)
                    .body(payload.bookingDetails())
                .when()
                    .delete("/booking/" + bookingId)
                .then()
                    .statusCode(anyOf(is(200), is(201)))
                    .extract().response().asString();
            
            System.out.println("Delete confirmation: "+response);
        }
	}

}
