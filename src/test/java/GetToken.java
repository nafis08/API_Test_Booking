package test.java;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import utils.payload;

import static io.restassured.RestAssured.*;

import POJO.AuthenticationToken;

public class GetToken {
	
	@Test
	public static void createToken() {
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		AuthenticationToken tk = given().header("Content-Type", "application/json")
		.body(payload.userCredentials()).when()
		.post("auth").as(AuthenticationToken.class);
		
		String token= tk.getToken();
		System.out.println("Token: "+token);
		

	}

}
