# API Test Booking

This project is an automated API testing framework for a booking system using **Java**, **RestAssured**, **TestNG**, and **Maven**. It includes CI support via **GitHub Actions**.

## Project Structure
```
API_Test_Booking-main/
├── .github/workflows/ # CI/CD pipeline configurations
├── src/ # Source code for test cases and utilities
├── testng.xml # TestNG suite configuration
├── pom.xml # Maven configuration file
├── .classpath, .project # Eclipse project files
├── .settings/ # Eclipse-specific settings
```

## Technologies Used

- Java
- Maven
- TestNG
- RestAssured
- GitHub Actions (for CI)

## Getting Started

### Prerequisites

- Java JDK 11 or higher
- Maven 3.x
- Internet connection to download dependencies

### Installation & Running Tests

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/API_Test_Booking-main.git
   cd API_Test_Booking-main
   ```
2. Run test:
   - With TestNG.
   - To run all tests together runt testing.xml as TestNG SUIT
 
## Features
Covers various API endpoints for booking systems

Request and response validations

Data-driven testing (if configured)

CI integration using GitHub Actions

## Continuous Integration
This project includes GitHub workflows:

ci.yml: Executes test cases on each push or pull request

nightly-run.yml: Executes tests daily to ensure API health

## Example Test Case
```
@Test
public void createBooking() {
    given().contentType("application/json")
           .body(requestPayload)
    .when().post("/booking")
    .then().statusCode(200)
           .body("bookingid", notNullValue());
}
```
