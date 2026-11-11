package pl.afernandes.apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;

/**
 * Wspólna konfiguracja dla wszystkich testów: adres API i typ danych.
 */
public abstract class BaseTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.requestSpecification = RestAssured.given().contentType(ContentType.JSON);
        // Przy błędzie test wypisze całe zapytanie i odpowiedź, co ułatwia debugowanie.
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
