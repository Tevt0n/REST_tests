package api.tests.demowebshop;

import io.qameta.allure.AllureId;
import org.junit.jupiter.api.*;

import static api.tests.demowebshop.Cookies.authCookieName;
import static api.tests.demowebshop.Cookies.login;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static api.helpers.CustomApiReportFilter.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@Tag("demowebshop")
@DisplayName("DemoWebShop tests")
public class ApiWithUiTests extends TestBase {

    @Test
    @AllureId("11470")
    @DisplayName("Successful authorization with API")
    void loginWithApiTest() {
        step("Get cookie by API and set it to browser", () -> {
            String authCookieValue = Cookies.getCookie();
            Cookies.setCookieToBrowser(authCookieValue);
        });
        step("Open main page", () ->
                open(""));
        step("Verify successful authorization", () ->
                $(".account").shouldHave(text(login)));
    }

    @Test
    @AllureId("11472")
    @DisplayName("Verify product is added to card with API")
    void addProductToCartWithApi() {
        String body = "product_attribute_72_5_18=53" +
                "&product_attribute_72_6_19=54" +
                "&product_attribute_72_3_20=57" +
                "&addtocart_72.EnteredQuantity=1";
        String authCookieValue = Cookies.getCookie();
        String cartSize = given()
                .filter(withCustomTemplates())
                .contentType("application/x-www-form-urlencoded")
                .body(body)
                .cookie(authCookieName, authCookieValue)
                .log().all()
                .when()
                .post("/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .body("updateflyoutcartsectionhtml", notNullValue())
                .body("updatetopcartsectionhtml", notNullValue())
                .extract()
                .path("updatetopcartsectionhtml");

        step("Set cookie to browser", () ->
                Cookies.setCookieToBrowser(authCookieValue));
        step("Open main page", () ->
                open(""));
        step("Verify cart size", () ->
                $("#topcartlink .cart-qty").shouldHave(text(cartSize)));
    }

    @Test
    @AllureId("11469")
    @DisplayName("Add product to cart as new user")
    void addProductToCartAsNewUserTest() {
        String body = "product_attribute_72_5_18=53" +
                "&product_attribute_72_6_19=54" +
                "&product_attribute_72_3_20=57" +
                "&addtocart_72.EnteredQuantity=1";

        given()
                .filter(withCustomTemplates())
                .contentType("application/x-www-form-urlencoded")
                .body(body)
                .log().all()
                .when()
                .post("/addproducttocart/details/72/1")
                .then()
                .log().all()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
                .body("updateflyoutcartsectionhtml", notNullValue())
                .body("updatetopcartsectionhtml", is("(1)"));
    }
}