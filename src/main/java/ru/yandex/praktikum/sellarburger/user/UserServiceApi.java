package ru.yandex.praktikum.sellarburger.user;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserServiceApi {

    private final String URL = "https://stellarburgers.nomoreparties.site";
    private static final String REGISTER_PATH = "/api/auth/register";
    private static final String LOGIN_PATH = "/api/auth/login";
    private static final String UPDATE_PATH = "/api/auth/user";
    private RequestSpecification requestSpec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBaseUri(URL)
            .build();

    @Step("Регистрация пользователя")
    public Response registerUser(UserData user) {
        return given()
                .spec(requestSpec)
                .body(user)
                .post(REGISTER_PATH);
    }

    @Step("Логин пользователя")
    public Response loginUser(UserData user) {
        return given()
                .spec(requestSpec)
                .body(user)
                .post(LOGIN_PATH);
    }

    @Step("Удаление пользователя")
    public Response deleteUser(String accessToken) {
        return given()
                .header("authorization", accessToken)
                .spec(requestSpec)
                .delete(UPDATE_PATH);
    }

    @Step("Обновление данных о пользователе с авторизацией")
    public Response updateUserWithToken(UserData user, String accessToken) {
        return given()
                .header("authorization", accessToken)
                .spec(requestSpec)
                .body(user)
                .patch(UPDATE_PATH);

    }

    @Step("Обновление данных о пользователе без авторизации")
    public Response updateUserWithoutToken(UserData user) {
        return given()
                .spec(requestSpec)
                .body(user)
                .patch(UPDATE_PATH);

    }
}
