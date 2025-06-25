package test.java;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utils.StoreBookingID;
import utils.TokenManager;
import utils.payload;

import java.util.List;

public class UpdateBooking {

    @Test(dependsOnMethods = {"test.java.GetToken.createToken","test.java.CreateBooking.createBooking"}) // Ensure CreateBooking runs first
    public void updateAllBookings() {
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
                    .put("/booking/" + bookingId)
                .then()
                    .statusCode(200)
                    .extract().response().asString();

            JsonPath js = new JsonPath(response);
            String firstname = js.getString("firstname");

            System.out.println("Updated Booking ID: " + bookingId + " | New First Name: " + firstname);
        }
    }
}
