package test.java;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utils.StoreBookingID;
import utils.TokenManager;
import utils.payload;

public class PartialUpdateBooking {
	
	@Test(dependsOnMethods = {"test.java.GetToken.createToken","test.java.CreateBooking.createBooking"})
    public void partialUpdateBooking() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        
        String token = TokenManager.getToken();
        System.out.println("Token: " + token);

        List<String> bookingIds = StoreBookingID.getBookingIds();
        
        for(String id: bookingIds) {
        	int bookingId = Integer.parseInt(id);
        	
        	String response = given()
            	    .contentType("application/json")
            	    .accept("application/json")
            	    .header("Cookie", "token=" + token)
            	    .body(payload.partialDetails())
            	.when()
            	    .patch("/booking/" + bookingId)
            	.then()
            	    .statusCode(200)
                .extract().response().asString();

            System.out.println("Response:\n" + response);

            JsonPath js = new JsonPath(response);
            String firstname = js.getString("firstname");
            System.out.println("Updated First Name: " + firstname);
        }
        
    }
}
