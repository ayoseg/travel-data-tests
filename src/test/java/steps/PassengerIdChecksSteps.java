package steps;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.annotations.Step;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.hamcrest.Matchers.is;

public class PassengerIdChecksSteps {


    private static String SEARCH_PATH = "/passenger/search";

    private static String STATUS_PATH = "/passenger/status";
    private static String STATUS_PATH1 = "/objects";
    private static String HISTORY_PATH = "/passenger/history";

    private Response response;

    @Step
    public void passengerSearch(File jsonFile) throws IOException {
        response = SerenityRest
                .given().
                contentType(ContentType.JSON)
                .body(jsonFile)
                .baseUri(Serenity.getCurrentSession().get("baseUrl").toString())
                .when()
                .post(SEARCH_PATH);
    }

    @Step
    public void passengerStatus(String jsonData){
        response = SerenityRest
                .given().
                contentType(ContentType.JSON)
                .body(jsonData)
                .baseUri(Serenity.getCurrentSession().get("baseUrl").toString())
                .when()
                .post(STATUS_PATH);
    }

    @Step
    public void passengerStatus1(String jsonFile){
        response = SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .body(jsonFile)
                .baseUri(Serenity.getCurrentSession().get("baseUrl").toString())
                .when()
                .post(STATUS_PATH1);
    }

    @Step
    public void passengerHistory(String jsonData){
        response = SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .body(jsonData)
                .baseUri(Serenity.getCurrentSession().get("baseUrl").toString())
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
    public void checkSearchResult(int countValue){
        response.body().prettyPrint();
        response.then().body("passenger_count", is(countValue));
    }

    public void checkStatusResult(int countValue, boolean value){
        response.then().body("passenger_count", is(countValue));
        response.then().body("out_of_country", is(value));
    }

    @Step
    public void checkHistoryResult(int countValue, Map<String, String> travelHistory){
        response.then().body("passenger_count", is(countValue));
        response.then().body("travel_history[0].scheduled_departure_datetime", is(travelHistory.get("scheduled_departure_datetime")));
        response.then().body("travel_history[0].scheduled_arrival_datetime", is(travelHistory.get("scheduled_arrival_datetime")));
        response.then().body("travel_history[0].departure_location", is(travelHistory.get("departure_location")));
        response.then().body("travel_history[0].arrival_location", is(travelHistory.get("arrival_location")));
        response.then().body("travel_history[0].carrier", is(travelHistory.get("carrier")));
        response.then().body("travel_history[0].route_id", is(travelHistory.get("route_id")));
        response.then().body("travel_history[0].route_direction", is(travelHistory.get("route_direction")));
        response.then().body("travel_history[0].passenger_status", is(travelHistory.get("passenger_status")));
        response.then().body("travel_history[0].travel_mode", is(travelHistory.get("travel_mode")));
    }

    @Step
    public void getStatus(int statusValue, String name){
        response.body().prettyPrint();
        response.then().body("data.year", is(statusValue));
        response.then().body("name", is(name));
    }

}
