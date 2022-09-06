package api.tests.demowebshop;

import api.helpers.DemowebshopCredentials;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;
import org.aeonbits.owner.ConfigFactory;

import static api.helpers.CustomApiReportFilter.withCustomTemplates;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;

public class Cookies {
    static DemowebshopCredentials credentials = ConfigFactory.create(DemowebshopCredentials.class);
    static String login = credentials.login(),
            password = credentials.password(),
            authCookieName = credentials.authCookieName();

    static String getAuthCookie() {
        String authCookieValue = given()
                .filter(withCustomTemplates())
                .contentType("application/x-www-form-urlencoded")
                .formParam("Email", login)
                .formParam("Password", password)
                .log().all()
                .when()
                .post("/login")
                .then()
                .log().all()
                .statusCode(302)
                .extract().cookie(authCookieName);
        return authCookieValue;
    }

    static void setCookieToBrowser(String authCookieValue) {
        open("/Themes/DefaultClean/Content/images/logo.png");
        Cookie authCookie = new Cookie(authCookieName, authCookieValue);
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
    }
}
