package pl.afernandes.apitests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Testy endpointu /posts: odczyt, filtrowanie, tworzenie, edycja i usuwanie.
 */
class PostsApiTest extends BaseTest {

    @Test
    @DisplayName("TC01: GET /posts zwraca listę 100 postów")
    void shouldReturnAllPosts() {
        given()
        .when()
            .get("/posts")
        .then()
            .statusCode(200)
            .body("size()", equalTo(100));
    }

    @Test
    @DisplayName("TC02: GET /posts/1 zwraca post o id 1 z wypełnionymi polami")
    void shouldReturnSinglePost() {
        given()
        .when()
            .get("/posts/1")
        .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("userId", equalTo(1))
            .body("title", not(emptyOrNullString()))
            .body("body", not(emptyOrNullString()));
    }

    @Test
    @DisplayName("TC03: GET /posts/9999 dla nieistniejącego posta zwraca 404")
    void shouldReturn404ForMissingPost() {
        given()
        .when()
            .get("/posts/9999")
        .then()
            .statusCode(404);
    }

    @Test
    @DisplayName("TC04: GET /posts?userId=1 zwraca tylko posty użytkownika 1")
    void shouldFilterPostsByUser() {
        given()
            .queryParam("userId", 1)
        .when()
            .get("/posts")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0))
            .body("userId", everyItem(equalTo(1)));
    }

    @Test
    @DisplayName("TC05: POST /posts tworzy nowy post i zwraca 201")
    void shouldCreatePost() {
        Map<String, Object> newPost = Map.of(
                "title", "Testowy post",
                "body", "Treść testowego posta",
                "userId", 1
        );

        given()
            .body(newPost)
        .when()
            .post("/posts")
        .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("title", equalTo("Testowy post"))
            .body("userId", equalTo(1));
    }

    @Test
    @DisplayName("TC06: PUT /posts/1 aktualizuje tytuł posta")
    void shouldUpdatePost() {
        Map<String, Object> updatedPost = Map.of(
                "id", 1,
                "title", "Zmieniony tytuł",
                "body", "Zmieniona treść",
                "userId", 1
        );

        given()
            .body(updatedPost)
        .when()
            .put("/posts/1")
        .then()
            .statusCode(200)
            .body("title", equalTo("Zmieniony tytuł"));
    }

    @Test
    @DisplayName("TC07: DELETE /posts/1 usuwa post i zwraca 200")
    void shouldDeletePost() {
        given()
        .when()
            .delete("/posts/1")
        .then()
            .statusCode(200);
    }
}
