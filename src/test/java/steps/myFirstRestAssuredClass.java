package steps;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class myFirstRestAssuredClass {

    @Test
    public void GetWeatherDetails() {
        RestAssured.baseURI = "https://demoqa.com/utilities/weather/city";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/Hyderabad");
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);
    }
      @Test
      public void getResponseBody(){

          RestAssured.given().queryParam("CUSTOMER_ID","68195")
                  .queryParam("PASSWORD","1234!")
                  .queryParam("Account_No","1")
                  .when().get("http://demo.guru99.com/V4/sinkministatement.php").then().log()
                  .body();
      }


}