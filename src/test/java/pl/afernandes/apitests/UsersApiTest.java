package pl.afernandes.apitests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Testy endpointu /users: poprawność danych użytkownika i jego powiązań.
 */
class UsersApiTest extends BaseTest {

    @Test
    @DisplayName("TC08: GET /users/1 zwraca użytkownika z poprawnym e-mailem")
    void shouldReturnUserWithValidEmail() {
        given()
        .when()
            .get("/users/1")
        .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("name", not(emptyOrNullString()))
            .body("email", containsString("@"))
            .body("address.city", not(emptyOrNullString()));
    }

    @Test
    @DisplayName("TC09: GET /users/1/posts zwraca posty należące do użytkownika 1")
    void shouldReturnPostsOfUser() {
        given()
        .when()
            .get("/users/1/posts")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("userId", everyItem(equalTo(1)));
    }

    @Test
    @DisplayName("TC10: odpowiedź API ma nagłówek Content-Type JSON")
    void shouldReturnJsonContentType() {
        given()
        .when()
            .get("/users")
        .then()
            .statusCode(200)
            .contentType(containsString("application/json"));
    }
}
