package utils;

import POJO.AuthenticationToken;
import static io.restassured.RestAssured.*;

public class TokenManager {

    private static String token;

    public static String getToken() {
        if (token == null) {
            token = createNewToken();
        }
        return token;
    }

    private static String createNewToken() {
        AuthenticationToken tk = given()
                .baseUri("https://restful-booker.herokuapp.com")
                .header("Content-Type", "application/json")
                .body(payload.userCredentials())
                .when()
                .post("/auth")
                .as(AuthenticationToken.class);

        return tk.getToken();
    }
}
