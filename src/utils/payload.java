package utils;

import org.testng.annotations.DataProvider;

public class payload {
	public static String userCredentials() {
		return "{\n"
				+ "    \"username\" : \"admin\",\n"
				+ "    \"password\" : \"password123\"\n"
				+ "}";
	}
	
	public static String bookingDetails() {
		return "{\n"
				+ "  \"firstname\" : \"Tsnk\",\n"
				+ "  \"lastname\" : \"Brown\",\n"
				+ "  \"totalprice\" : 111,\n"
				+ "  \"depositpaid\" : true,\n"
				+ "  \"bookingdates\" : {\n"
				+ "    \"checkin\" : \"2018-01-01\",\n"
				+ "    \"checkout\" : \"2019-01-01\"\n"
				+ "  },\n"
				+ "  \"additionalneeds\" : \"Lunch\"\n"
				+ "}\n"
				+ "";
	}
	
	public static String partialDetails() {
		return "{\n"
				+ "  \"firstname\": \"Nazmus\",\n"
				+ "  \"lastname\": \"Nafis\"\n"
				+ "}\n"
				+ "";
	}
	
	public static String createBooking(String firstName, String lastName, String additionalNeeds) {
		
		String body = "{\n"
				+ "    \"firstname\": \""+ firstName +"\",\n"
				+ "    \"lastname\": \""+ lastName +"\",\n"
				+ "    \"totalprice\": 10999,\n"
				+ "    \"depositpaid\": true,\n"
				+ "    \"bookingdates\": {\n"
				+ "        \"checkin\": \"2025-01-01\",\n"
				+ "        \"checkout\": \"2026-01-01\"\n"
				+ "    },\n"
				+ "    \"additionalneeds\": \""+ additionalNeeds +"\"\n"
				+ "}";
		
		return body;
	}
	
	public static String incompleteBody() {
		return "{ \"lastname\": \"Smith\", \"totalprice\": 123, \"depositpaid\": true, \"bookingdates\": { \"checkin\": \"2023-01-01\", \"checkout\": \"2023-01-10\" } }";
	}
	
	@DataProvider(name="BookingData")
	public Object[][] getData() {
		return new Object[][] {{"Francis","Xxevier", "Rice"},
								{"Rick","Sorken", "Noodle"},
								{"Danny","Raegan", "Car"}};
	}

}
