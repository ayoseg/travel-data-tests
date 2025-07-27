package utils;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.util.EnvironmentVariables;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class BeforeAndAfterHooks {
    EnvironmentVariables environmentVariables;
    @Before
    public void setEnvBaseUrl() {
        String base_url = EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("api.base.url");
        Serenity.setSessionVariable("baseUrl").to(base_url);
    }
}
