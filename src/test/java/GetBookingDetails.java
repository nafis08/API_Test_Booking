package test.java;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class GetBookingDetails {
	
	@Test
    public void getBookingDetails() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";

        int bookingId = 589;  // Replace with desired ID

        //BookingDetails booking = 
        	String response = given()
            .log().uri()
            .when()
            .get("/booking/" + bookingId)
            .then().assertThat()
            .statusCode(200).extract().response().asString();
        	
        	System.out.println(response);
        	JsonPath js = new JsonPath(response);
        	String firstname = js.getString("firstname");
        	System.out.println(firstname);
    }
}
