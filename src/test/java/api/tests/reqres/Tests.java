package api.tests.reqres;

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
public class Tests {
    @Test
    @DisplayName("Get list of users")
    void getListUsers() {
        given()
                .spec(request)
                .when()
                .get("/users")
                .then()
                .log().all()
                .spec(responseSpec)
                .body("page", is(1))
                .body("per_page", is(6))
                .body("total", is(12))
                .body("total_pages", is(2));
    }

    @Test
    @DisplayName("Create new user")
    void createNewUser() {
        String body = "{\"name\": \"morpheus\",     \"job\": \"leader\" }";

        given()
                .spec(request)
                .body(body)
                .when()
                .post("/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    @DisplayName("Update user info")
    void updateUser() {
        String body = "{\"name\": \"morpheus\",\"job\": \"zion resident\"}";

        given()
                .spec(request)
                .body(body)
                .when()
                .put("/users/2")
                .then()
                .log().all()
                .spec(responseSpec)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));
    }

    @Test
    @DisplayName("Register new user")
    void registerUser() {
        String body = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";

        given()
                .spec(request)
                .body(body)
                .when()
                .post("/register")
                .then()
                .log().all()
                .spec(responseSpec)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    @DisplayName("Log in test")
    void loginTest() {
        String body = "{\"email\": \"eve.holt@reqres.in\",     \"password\": \"cityslicka\" }";

        given()
                .spec(request)
                .body(body)
                .when()
                .post("/login")
                .then()
                .log().all()
                .spec(responseSpec)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    @DisplayName("Verify user data")
    void verifyUserData() {
        LombokUserData data = given()
                .spec(request)
                .when()
                .get("/users/2")
                .then()
                .spec(responseSpec)
                .log().body()
                .extract().as(LombokUserData.class);
        assertEquals(2, data.getUser().getId());
        assertEquals("janet.weaver@reqres.in", data.getUser().getEmail());
        assertEquals("Janet", data.getUser().getFirstName());
        assertEquals("Weaver", data.getUser().getLastName());
    }
}