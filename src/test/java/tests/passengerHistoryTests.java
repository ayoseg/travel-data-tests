package tests;

import com.google.gson.JsonObject;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import steps.PassengerIdChecksSteps;
import utils.BeforeAndAfterHooks;
import utils.JsonFileHelper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SerenityRunner.class)
public class passengerHistoryTests {

    @Steps
    PassengerIdChecksSteps passengerIdChecksSteps;

    @Steps
    JsonFileHelper jsonFileHelper;

    @Steps
    BeforeAndAfterHooks hook;

    @Before
    public void setBaseUrl(){
        hook.setEnvBaseUrl();
    }

    @Test
    public void passengerHistory() throws URISyntaxException, IOException {
        File jsonFile = jsonFileHelper.getJsonDataFile("testdata/passengerDetails{0}.json");
        JsonObject newJsonData = jsonFileHelper.addProperty(jsonFile, "earliest_departure_date", "2023-01-01");
        passengerIdChecksSteps.passengerHistory(jsonFileHelper.jsonObjectToString(newJsonData));
        passengerIdChecksSteps.isSuccessResponse();

        Map<String, String> travelHistoryDetails = new HashMap<>();
        travelHistoryDetails.put("scheduled_departure_datetime", "2024-01-01T10:00:00.000Z");
        travelHistoryDetails.put("scheduled_arrival_datetime", "2024-01-01T15:00:00.000Z");
        travelHistoryDetails.put("departure_location", "LHR");
        travelHistoryDetails.put("arrival_location", "LAX");
        travelHistoryDetails.put("carrier", "British Airways");
        travelHistoryDetails.put("route_id", "BA123");
        travelHistoryDetails.put("route_direction", "OUTBOUND");
        travelHistoryDetails.put("passenger_status", "DC");
        travelHistoryDetails.put("travel_mode", "Air Passenger");

        passengerIdChecksSteps.checkHistoryResult(1, travelHistoryDetails);
    }

}
