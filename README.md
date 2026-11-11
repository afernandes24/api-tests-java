# 🧪 Automatyczne testy REST API (Java)

**Projekt z automatycznymi testami REST API napisanymi w Javie (JUnit 5, REST Assured).**

Testy sprawdzają publiczne API [JSONPlaceholder](https://jsonplaceholder.typicode.com): kody odpowiedzi HTTP, poprawność danych JSON, filtrowanie danych, obsługę błędów oraz operacje CRUD (tworzenie, odczyt, edycja, usuwanie).

![API tests](https://github.com/afernandes24/api-tests-java/actions/workflows/tests.yml/badge.svg)

---

## 🎯 Cel projektu

- ✅ Zaprojektowanie scenariuszy i przypadków testowych dla REST API
- ✅ Automatyzacja testów w Javie
- ✅ Weryfikacja kodów HTTP, danych JSON i obsługi błędów
- ✅ Automatyczne uruchamianie testów po każdym pushu

---

## 🛠️ Technologie

![Java](https://img.shields.io/badge/Java_17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit_5-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![REST Assured](https://img.shields.io/badge/REST_Assured-5A9E4B?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white)

---

## 📋 Scenariusze testowe

| ID | Endpoint | Scenariusz | Oczekiwany wynik |
| --- | --- | --- | --- |
| TC01 | `GET /posts` | Pobranie wszystkich postów | 200, lista 100 postów |
| TC02 | `GET /posts/1` | Pobranie jednego posta | 200, id = 1, wypełnione pola `title` i `body` |
| TC03 | `GET /posts/9999` | Pobranie nieistniejącego posta | 404 |
| TC04 | `GET /posts?userId=1` | Filtrowanie postów po użytkowniku | 200, każdy post ma `userId = 1` |
| TC05 | `POST /posts` | Utworzenie nowego posta | 201, zwrócone `id` i przesłane dane |
| TC06 | `PUT /posts/1` | Edycja posta | 200, zmieniony tytuł |
| TC07 | `DELETE /posts/1` | Usunięcie posta | 200 |
| TC08 | `GET /users/1` | Dane użytkownika | 200, e-mail zawiera `@`, wypełnione miasto |
| TC09 | `GET /users/1/posts` | Posty użytkownika | 200, każdy post ma `userId = 1` |
| TC10 | `GET /users` | Nagłówek odpowiedzi | `Content-Type: application/json` |

---

## 📁 Struktura projektu

```
src/test/java/pl/afernandes/apitests/
├── BaseTest.java        # wspólna konfiguracja (adres API, logowanie błędów)
├── PostsApiTest.java    # testy endpointu /posts (TC01–TC07)
└── UsersApiTest.java    # testy endpointu /users (TC08–TC10)
.github/workflows/tests.yml   # automatyczne uruchamianie testów
```

---

## ▶️ Jak uruchomić

Wymagania: **Java 17** i **Maven**.

```bash
git clone https://github.com/afernandes24/api-tests-java.git
cd api-tests-java
mvn test
```

---

## ℹ️ Uwagi

JSONPlaceholder to testowe API: operacje `POST`, `PUT` i `DELETE` zwracają poprawne odpowiedzi, ale nie zapisują zmian na serwerze.
