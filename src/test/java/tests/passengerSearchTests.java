package tests;

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

@RunWith(SerenityRunner.class)
public class passengerSearchTests {

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
    public void searchForPassenger() throws URISyntaxException, IOException {
        File jsonFile = jsonFileHelper.getJsonDataFile("testdata/passengerDetails{0}.json");
        passengerIdChecksSteps.passengerSearch(jsonFile);
        passengerIdChecksSteps.isSuccessResponse();
        passengerIdChecksSteps.checkSearchResult(1);
    }

}
