package сourier_tests;

import base_test.BaseTestAfterCourier;
import base_test.api_base.CourierApi;
import base_test.data.CourierData;
import base_test.data.CourierDataGenerate;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AuthorizationCourierTests extends BaseTestAfterCourier {

    @DisplayName("Авторизация курьера с полями обязательными полями: login, password")
    @Description("Проверяем код ответа авторизации курьера 200 и содержание body ответа \"id\": непустое")
    @Test
    public void testAuthorizationSuccess() {

        courierData = new CourierData();
        courierData.setLogin(CourierDataGenerate.generateLogin());
        courierData.setPassword(CourierDataGenerate.generatePassword());

        courierApi = new CourierApi();

        courierApi.createCourier(courierData);
        Response response = courierApi.authorizationCourier(courierData);

        assertThat("Код ответа должен быть 200, пришел " + response.getStatusCode(), response.getStatusCode(), equalTo(HttpStatus.SC_OK));
        assertThat("В теле ответа должно прийти \"id\": int, пришло \"id\": " + response.jsonPath().getInt("id"), response.jsonPath().getInt("id"), notNullValue());
    }

    @DisplayName("Авторизация курьера несуществующим login")
    @Description("Проверяем код ответа авторизации курьера 404 и содержание body ответа \"message\": \"Учетная запись не найдена\"")
    @Test
    public void testAuthorizationWithWrongLogin() {

        courierData = new CourierData();
        courierData.setLogin(CourierDataGenerate.generateLogin());
        courierData.setPassword(CourierDataGenerate.generatePassword());

        courierApi = new CourierApi();

        courierApi.createCourier(courierData);

        CourierData authCourierData = new CourierData();
        authCourierData.setLogin(courierData.getLogin() + "123");
        authCourierData.setPassword(courierData.getPassword());

        Response response = courierApi.authorizationCourier(authCourierData);

        assertThat("Код ответа должен быть 404, пришел " + response.getStatusCode(), response.getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
        assertThat("В теле ответа должно прийти \"message\": \"Учетная запись не найдена\", пришло \"message\": " + response.jsonPath().getString("message"), response.jsonPath().getString("message"), equalTo("Учетная запись не найдена"));
    }

    @DisplayName("Авторизация курьера несуществующим password")
    @Description("Проверяем код ответа авторизации курьера 404 и содержание body ответа \"message\": \"Учетная запись не найдена\"")
    @Test
    public void testAuthorizationWithWrongPassword() {

        courierData = new CourierData();
        courierData.setLogin(CourierDataGenerate.generateLogin());
        courierData.setPassword(CourierDataGenerate.generatePassword());

        courierApi = new CourierApi();

        courierApi.createCourier(courierData);

        CourierData authCourierData = new CourierData();
        authCourierData.setLogin(courierData.getLogin());
        authCourierData.setPassword(courierData.getPassword() + "123");

        Response response = courierApi.authorizationCourier(authCourierData);

        assertThat("Код ответа должен быть 404, пришел " + response.getStatusCode(), response.getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
        assertThat("В теле ответа должно прийти \"message\": \"Учетная запись не найдена\", пришло \"message\": " + response.jsonPath().getString("message"), response.jsonPath().getString("message"), equalTo("Учетная запись не найдена"));
    }

    @DisplayName("Авторизация курьера передаем только password")
    @Description("Проверяем код ответа авторизации курьера 400 и содержание body ответа \"message\": \"Недостаточно данных для входа\"")
    @Test
    public void testAuthorizationWithNoLogin() {

        courierData = new CourierData();
        courierData.setLogin(CourierDataGenerate.generateLogin());
        courierData.setPassword(CourierDataGenerate.generatePassword());

        courierApi = new CourierApi();

        courierApi.createCourier(courierData);

        CourierData authCourierData = new CourierData();
        authCourierData.setPassword(courierData.getPassword());

        Response response = courierApi.authorizationCourier(authCourierData);

        assertThat("Код ответа должен быть 400, пришел " + response.getStatusCode(), response.getStatusCode(), equalTo(HttpStatus.SC_BAD_REQUEST));
        assertThat("В теле ответа должно прийти \"message\": \"Недостаточно данных для входа\", пришло \"message\": " + response.jsonPath().getString("message"), response.jsonPath().getString("message"), equalTo("Недостаточно данных для входа"));
    }

    @DisplayName("Авторизация курьера передаем только login")
    @Description("Проверяем код ответа авторизации курьера 400 и содержание body ответа \"message\": \"Недостаточно данных для входа\"")
    @Test
    public void testAuthorizationWithNoPassword() {

        courierData = new CourierData();
        courierData.setLogin(CourierDataGenerate.generateLogin());
        courierData.setPassword(CourierDataGenerate.generatePassword());

        courierApi = new CourierApi();

        courierApi.createCourier(courierData);

        CourierData authCourierData = new CourierData();
        authCourierData.setLogin(courierData.getLogin());

        Response response = courierApi.authorizationCourier(authCourierData);

        assertThat("Код ответа должен быть 400, пришел " + response.getStatusCode(), response.getStatusCode(), equalTo(HttpStatus.SC_BAD_REQUEST));
        assertThat("В теле ответа должно прийти \"message\": \"Недостаточно данных для входа\", пришло \"message\": " + response.jsonPath().getString("message"), response.jsonPath().getString("message"), equalTo("Недостаточно данных для входа"));
    }
}
