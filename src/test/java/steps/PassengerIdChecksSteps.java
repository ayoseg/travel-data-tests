package steps;

import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.annotations.Step;

import static org.hamcrest.Matchers.is;

public class PassengerIdChecksSteps {

    //private static String BASE_URL = "https://test.com";
    private static String BASE_URL = "https://api.genderize.io";
    private static String BASE_URL1 = "https://api.restful-api.dev";
    //private static String SEARCH_PATH = "/passenger/search";
    private static String SEARCH_PATH = "?name=luc";
    //private static String STATUS_PATH = "/passenger/status";
    private static String STATUS_PATH = "/objects";
    private static String HISTORY_PATH = "/passenger/history";
    private Response response;

 //   @Step
//    public void passengerSearch(String payload){
//        response = SerenityRest
//                .given().
//                contentType(ContentType.JSON)
//                .body(payload)
//                .baseUri(BASE_URL)
//                .when()
//                .post(SEARCH_PATH);
//    }
    @Step
    public void passengerSearch(){
        response = SerenityRest
                .given()
                .baseUri(BASE_URL)
                .when()
                .get(SEARCH_PATH);
    }

    @Step
    public void passengerStatus(String payload){
        response = SerenityRest
                .given().
                contentType(ContentType.JSON)
                .body(payload)
                .baseUri(BASE_URL)
                .when()
                .post(STATUS_PATH);
    }

    @Step
    public void passengerStatus1(String payload){
        response = SerenityRest
                .given().
                contentType(ContentType.JSON)
                .body(payload.toString())
                .baseUri(BASE_URL1)
                .when()
                .post(STATUS_PATH);
    }

    @Step
    public void passengerHistory(String payload){
        response = SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .body(payload)
                .baseUri(BASE_URL)
                .when()
                .post(HISTORY_PATH);
    }

    @Step
    public void isSuccessResponse(){
        response.then().statusCode(200);
    }

    public void isFailedResponse(int responseCode){
        response.then().statusCode(responseCode);
    }

    @Step
    public void iShouldFindPassengerStatus(String statusValue){
        response.then().body("RestResponse.result.status", is(statusValue));
    }

    @Step
    public void getName(String statusValue){
        response.body().prettyPrint();
        response.then().body("name", is(statusValue));
    }

    @Step
    public void getGender(String statusValue){
        response.then().body("gender", is(statusValue));
    }

    @Step
    public void getName1(String model){
        response.then().body("name", is(model));
    }

    @Step
    public void getStatus(int statusValue){
        response.body().prettyPrint();
        response.then().body("data.year", is(statusValue));
    }

}
