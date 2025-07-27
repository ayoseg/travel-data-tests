package tests;


import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import steps.PassengerIdChecksSteps;
import utils.BeforeAndAfterHooks;

import java.io.File;
import java.net.URISyntaxException;

@RunWith(SerenityRunner.class)
public class quickTest {

    @Steps
    PassengerIdChecksSteps passengerIdChecksSteps;

    @Steps
    BeforeAndAfterHooks hook;

    @Before
    public void setBaseUrl(){
        hook.setEnvBaseUrl();
    }

    @Test
    public void getMacDetails() throws URISyntaxException {

        File jsonData = new File(this.getClass().getClassLoader().getResource("testdata/macdetails.json").toURI());
        passengerIdChecksSteps.passengerStatus1(jsonData);
        passengerIdChecksSteps.isSuccessResponse();
        passengerIdChecksSteps.getStatus(2019);
        passengerIdChecksSteps.getName1("Apple MacBook Pro 16");

    }
}

