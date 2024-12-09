package base_test.api_base;

import base_test.constants.UrlConstants;
import base_test.data.CourierData;

import io.qameta.allure.Step;
import io.restassured.response.Response;


import static base_test.constants.UrlConstants.*;
import static io.restassured.RestAssured.*;

public class CourierApi extends RestApiBuilder {

    Response response;
    private final RestApiBuilder restApiBuilder = new RestApiBuilder();

    @Step("Создание курьера в системе")
    public Response createCourier(CourierData courierData) {

            response =
                    given()
                            .spec(restApiBuilder.requestSpecification())
                            .body(courierData)
                            .post(COURIER_CREATE);

            return response;
    }

    @Step("Авторизация курьера в системе")
    public Response authorizationCourier(CourierData courierData) {

            CourierData authCourierData = new CourierData();
            authCourierData.setLogin(courierData.getLogin());
            authCourierData.setPassword(courierData.getPassword());

            response =
                    given()
                            .spec(restApiBuilder.requestSpecification())
                            .body(authCourierData)
                            .post(COURIER_LOGIN);

        return response;
        }

    @Step("Удаление курьера из системы")
    public void deleteCourier(CourierData courierData) {
        //авторизуемся и получаем id курьера в системе
        authorizationCourier(courierData);
        getCourierId(courierData);
        UrlConstants.setId(courierData.getId());

        response =
                given()
                        .spec(restApiBuilder.requestSpecification())
                        .delete(COURIER_DELETE);
    }

    @Step("Получение количества заказов ")
    public void getCourierOrderList(CourierData courierData) {
        //авторизуемся и получаем id курьера в системе
        authorizationCourier(courierData);
        getCourierId(courierData);
        UrlConstants.setId(courierData.getId());

        response =
                given()
                        .spec(restApiBuilder.requestSpecification())
                        .delete(COURIER_ORDER_LIST);
    }

    @Step("Получение id курьера")
    public void getCourierId(CourierData courierData) {
        //достаем id созданного курера и записываем в текущий экземпляр класса курьера
        try {
            courierData.setId(response.jsonPath().getString("id"));
        } catch (Exception ignored) {
        }
    }
}
