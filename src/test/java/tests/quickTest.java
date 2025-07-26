package tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

import steps.PassengerIdChecksSteps;
@RunWith(SerenityRunner.class)
public class quickTest {

    @Steps
    PassengerIdChecksSteps passengerIdChecksSteps;

    @Test
    public void verifyPassengerSearch(){
        passengerIdChecksSteps.passengerSearch();
        passengerIdChecksSteps.getName("luc");
        passengerIdChecksSteps.getGender("male");
    }

String toJson  = "{\n" +
        "   \"name\": \"Apple MacBook Pro 16\",\n" +
        "   \"data\": {\n" +
        "      \"year\": 2019,\n" +
        "      \"price\": 1849.99,\n" +
        "      \"CPU model\": \"Intel Core i9\",\n" +
        "      \"Hard disk size\": \"1 TB\"\n" +
        "   }\n" +
        "}";
    //JsonObject convertToJson = new Gson().fromJson(toJson, JsonElement.class).getAsJsonObject();
    @Test
    public void getMacDetails(){
        passengerIdChecksSteps.passengerStatus1(toJson);
        passengerIdChecksSteps.isSuccessResponse();
        passengerIdChecksSteps.getStatus(2019);
        passengerIdChecksSteps.getName1("Apple MacBook Pro 16");

    }
}

