package api.tests.demowebshop;

import api.helpers.DriverConfig;
import api.helpers.DriverUtils;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.hasWebDriverStarted;

public class TestBase {
    @BeforeAll
    static void configure() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DriverConfig driverConfig = ConfigFactory.create(DriverConfig.class, System.getProperties());
        Configuration.browser = driverConfig.browser();
        Configuration.browserVersion =  driverConfig.browserVersion();
        Configuration.browserSize =  driverConfig.browserSize();
        Configuration.baseUrl =  driverConfig.baseUrl();
        Configuration.remote = driverConfig.selenoidUrl();
        RestAssured.baseURI = driverConfig.baseUrl();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        if (hasWebDriverStarted()) {
            DriverUtils.screenshotAs("Last screenshot");
            DriverUtils.pageSource();
            DriverUtils.addBrowserConsoleLogs();
            DriverUtils.addVideo();
            closeWebDriver();
        }
    }

}
