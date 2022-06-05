package in.reqres;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ApiTests {
    @Test
    void getListUsers() {
        given()
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .log().all()
                .body("page",is(1))
                .body("per_page", is(6))
                .body("total",is(12))
                .body("total_pages",is(2));
    }

    @Test
    void createNewUser() {
        String body = "{\"name\": \"morpheus\",     \"job\": \"leader\" }";

        given()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(201)
                .body("name",is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    void updateUser() {
        String body = "{\"name\": \"morpheus\",\"job\": \"zion resident\"}";

        given()
                .body(body)
                .contentType(JSON)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body("name",is("morpheus"))
                .body("job", is("zion resident"));
    }

    @Test
    void registerUser() {
        String body = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";

        given()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().all()
                .statusCode(200)
                .body("id",is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void loginWithoutPassword() {
        String body = "{\"email\": \"eve.holt@reqres.in\"}";

        given()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().all()
                .statusCode(400)
                .body("error", is("Missing password"));

    }

}
