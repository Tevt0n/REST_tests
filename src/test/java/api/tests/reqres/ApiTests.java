package api.tests.reqres;

import api.models.User;
import api.models.UserData;
import io.qameta.allure.AllureId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.tests.reqres.Specs.request;
import static api.tests.reqres.Specs.responseSpec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("reqres")
@DisplayName("Reqres tests")
public class ApiTests {

    private final String token = "QpwL5tke4Pnpja7X4";

    @Test
    @AllureId("11474")
    @DisplayName("Create new user")
    void createNewUser() {
        User body = new User();
        body.setName("morpheus");
        body.setJob("leader");
        User user = given()
                .spec(request)
                .body(body)
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201)
                .extract().as(User.class);
        assertEquals(body.getName(), user.getName());
        assertEquals(body.getJob(), user.getJob());
    }

    @Test
    @AllureId("11468")
    @DisplayName("Update user info")
    void updateUser() {
        User body = new User();
        body.setName("morpheus");
        body.setJob("zion resident");
        User user = given()
                .spec(request)
                .body(body)
                .when()
                .put("/users/2")
                .then()
                .log().all()
                .spec(responseSpec)
                .extract().as(User.class);
        assertEquals(body.getName(), user.getName());
        assertEquals(body.getJob(), user.getJob());
    }

    @Test
    @AllureId("11467")
    @DisplayName("Register new user")
    void registerUser() {
        User user = new User();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("cityslicka");
        given()
                .spec(request)
                .body(user)
                .when()
                .post("/register")
                .then()
                .log().all()
                .spec(responseSpec)
                .body("id", is(4))
                .body("token", is(token));
    }

    @Test
    @AllureId("11471")
    @DisplayName("Log in test")
    void loginTest() {
        User body = new User();
        body.setEmail("eve.holt@reqres.in");
        body.setPassword("cityslicka");
        given()
                .spec(request)
                .body(body)
                .when()
                .post("/login")
                .then()
                .log().all()
                .spec(responseSpec)
                .body("token", is(token));
    }

    @Test
    @AllureId("11473")
    @DisplayName("Verify user data")
    void verifyUserData() {
        UserData data = given()
                .spec(request)
                .when()
                .get("/users/2")
                .then()
                .spec(responseSpec)
                .log().body()
                .extract().as(UserData.class);
        assertEquals(2, data.getUser().getId());
        assertEquals("janet.weaver@reqres.in", data.getUser().getEmail());
        assertEquals("Janet", data.getUser().getFirstName());
        assertEquals("Weaver", data.getUser().getLastName());
    }
}