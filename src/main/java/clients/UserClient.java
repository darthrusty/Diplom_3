package clients;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import pojo.CreateUser;
import pojo.LoginUser;

import static io.restassured.RestAssured.given;

public class UserClient extends BaseClient {

    private static final String REGISTER_API_URL = "api/auth/register";
    private static final String LOGIN_API_URL = "api/auth/login";
    private static final String CHANGE_API_URL = "api/auth/user";

    @Step("Вход пользователя")
    public ValidatableResponse login(LoginUser loginUser) {
        return given()
                .spec(getSpec())
                .body(loginUser)
                .when()
                .post(LOGIN_API_URL)
                .then();
    }

    @Step("Удаление пользователя")
    public ValidatableResponse delete(String accessToken) {
        return given()
                .spec(getSpec())
                .header("Authorization", accessToken)
                .when()
                .delete(CHANGE_API_URL)
                .then();
    }

    @Step("Создание пользователя")
    public ValidatableResponse create(CreateUser createUser) {
        return given()
                .spec(getSpec())
                .body(createUser)
                .when()
                .post(REGISTER_API_URL)
                .then();
    }

}
