package steps;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.di.SerenityInfrastructure;

import java.io.File;

import static org.hamcrest.Matchers.is;

public class PassengerIdChecksSteps {


    private static String SEARCH_PATH = "/passenger/search";

    //private static String STATUS_PATH = "/passenger/status";
    private static String STATUS_PATH = "/objects";
    private static String HISTORY_PATH = "/passenger/history";

    private Response response;

    @Step
    public void passengerSearch(String payload){
        response = SerenityRest
                .given().
                contentType(ContentType.JSON)
                .body(payload)
                .baseUri(Serenity.getCurrentSession().get("baseUrl").toString())
                .when()
                .post(SEARCH_PATH);
    }

    @Step
    public void passengerStatus(String payload){

        response = SerenityRest
                .given().
                contentType(ContentType.JSON)
                .body(payload)
                .baseUri(Serenity.getCurrentSession().get("baseUrl").toString())
                .when()
                .post(STATUS_PATH);
    }

    @Step
    public void passengerStatus1(File jsonData){
        response = SerenityRest
                .given().
                contentType(ContentType.JSON)
                .body(jsonData)
                .baseUri(Serenity.getCurrentSession().get("baseUrl").toString())
                .when()
                .post(STATUS_PATH);
    }

    @Step
    public void passengerHistory(String payload){
        response = SerenityRest
                .given()
                .contentType(ContentType.JSON)
                .body(payload)
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
    public void getName1(String model){
        response.then().body("name", is(model));
    }

    @Step
    public void getStatus(int statusValue){
        response.body().prettyPrint();
        response.then().body("data.year", is(statusValue));
    }

}
