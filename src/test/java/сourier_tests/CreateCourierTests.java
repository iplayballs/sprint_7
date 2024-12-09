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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;



public class CreateCourierTests extends BaseTestAfterCourier {

    @DisplayName("Создание курьера с полями обязательными полями: login, password и с необязательным полем firstName")
    @Description("Проверяем код ответа создания курьера 201 и содержание body ответа \"ok\": true")
    @Test
public void testCreateCourierWithRequiredAndOptionalFields() {

    courierData = new CourierData();
    courierData.setLogin(CourierDataGenerate.generateLogin());
    courierData.setPassword(CourierDataGenerate.generatePassword());
    courierData.setFirstName(CourierDataGenerate.generateFirstName());

    courierApi = new CourierApi();

    Response response = courierApi.createCourier(courierData);

    assertThat("Код ответа должен быть 201, пришел " + response.getStatusCode(), response.getStatusCode(), equalTo(HttpStatus.SC_CREATED));
    assertThat("В теле ответа должно прийти \"ok\": true, пришло \"ok\": " + response.jsonPath().getBoolean("ok"), response.jsonPath().getBoolean("ok"), equalTo(true));
}

    @DisplayName("Создание курьера с полями обязательными полями: login, password")
    @Description("Проверяем код ответа создания курьера 201 и содержание body ответа \"ok\": true")
    @Test
    public void testCreateCourierWithRequiredFields() {
        courierData = new CourierData();
        courierData.setLogin(CourierDataGenerate.generateLogin());
        courierData.setPassword(CourierDataGenerate.generatePassword());

        courierApi = new CourierApi();

        Response response = courierApi.createCourier(courierData);

        assertThat("Код ответа должен быть 201, пришел " + response.getStatusCode(), response.getStatusCode(), equalTo(HttpStatus.SC_CREATED));
        assertThat("В теле ответа должно прийти \"ok\": true, пришло \"ok\": " + response.jsonPath().getBoolean("ok"), response.jsonPath().getBoolean("ok"), equalTo(true));
    }

    @DisplayName("Создание курьера с полями обязательным полем login")
    @Description("Проверяем код ответа создания курьера 400 и содержание body ответа \"message\": \"Недостаточно данных для создания учетной записи\"")
    @Test
    public void testCreateCourierWithRequiredFieldLogin() {
        courierData = new CourierData();
        courierData.setLogin(CourierDataGenerate.generateLogin());

        courierApi = new CourierApi();

        Response response = courierApi.createCourier(courierData);

        assertThat("Код ответа должен быть 400, пришел " + response.getStatusCode(), response.getStatusCode(), equalTo(HttpStatus.SC_BAD_REQUEST));
        assertThat("В теле ответа должно прийти \"message\": \"Недостаточно данных для создания учетной записи\", пришло \"message\": " + response.jsonPath().getString("message"), response.jsonPath().getString("message"), equalTo("Недостаточно данных для создания учетной записи"));
    }

    @DisplayName("Создание курьера с полями обязательным полем password")
    @Description("Проверяем код ответа создания курьера 400 и содержание body ответа \"message\": \"Недостаточно данных для создания учетной записи\"")
    @Test
    public void testCreateCourierWithRequiredFieldPassword() {
        courierData = new CourierData();
        courierData.setPassword(CourierDataGenerate.generatePassword());

        courierApi = new CourierApi();

        Response response = courierApi.createCourier(courierData);

        assertThat("Код ответа должен быть 400, пришел " + response.getStatusCode(), response.getStatusCode(), equalTo(HttpStatus.SC_BAD_REQUEST));
        assertThat("В теле ответа должно прийти \"message\": \"Недостаточно данных для создания учетной записи\", пришло \"message\": " + response.jsonPath().getString("message"), response.jsonPath().getString("message"), equalTo("Недостаточно данных для создания учетной записи"));
    }

    @DisplayName("Создание курьера с уже существующим login")
    @Description("Проверяем код ответа создания курьера 400 и содержание body ответа \"message\": \"Недостаточно данных для создания учетной записи\"")
    @Test
    public void testCreateCourierWithExistLogin() {
        courierData = new CourierData();
        courierData.setLogin(CourierDataGenerate.generateLogin());
        courierData.setPassword(CourierDataGenerate.generatePassword());

        courierApi = new CourierApi();
        //создаем первого пользователя
        courierApi.createCourier(courierData).then().statusCode(HttpStatus.SC_CREATED);
        courierData.setPassword(CourierDataGenerate.generatePassword());
        Response response = courierApi.createCourier(courierData);

        assertThat("Код ответа должен быть 409, пришел " + response.getStatusCode(), response.getStatusCode(), equalTo(409));
        assertThat("В теле ответа должно прийти \"message\": \"Этот логин уже используется\", пришло \"message\": " + response.jsonPath().getString("message"), response.jsonPath().getString("message"), equalTo("Этот логин уже используется"));
    }

}