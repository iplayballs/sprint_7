package base_test.api_base;

import base_test.data.OrderData;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static base_test.constants.UrlConstants.GET_ORDER_LIST;
import static base_test.constants.UrlConstants.ORDER_CREATE;
import static io.restassured.RestAssured.given;

public class OrderApi extends RestApiBuilder {

    Response response;
    private final RestApiBuilder restApiBuilder = new RestApiBuilder();

    @Step("Создание одного заказа самоката в системе")
    public Response createOneOrder(OrderData orderData) {

        response =
                given()
                        .spec(restApiBuilder.requestSpecification())
                        .body(orderData)
                        .post(ORDER_CREATE);

        return response;
    }

    @Step("Создание нескольких заказов: самоката в системе")
    public void createSomeOrders(OrderData orderData, int numberOfOrdersToCreate) {

        for (int i = 0; i < numberOfOrdersToCreate; i++) {
            response =
                    given()
                            .spec(restApiBuilder.requestSpecification())
                            .body(orderData)
                            .post(ORDER_CREATE);
        }
    }

    @Step("Запрос списка заказов со значением limit = {limit}")
    public Response getOrderListLimit (int  limit) {

        response =
                given()
                        .spec(restApiBuilder.requestSpecification())
                        .queryParam("limit", limit)
                        .get(GET_ORDER_LIST);

        return response;
    }

    @Step("Запрос списка заказов со значением limit = 30 и nearestStation = {nearestStation}")
    public Response getOrderListStation (String  nearestStation) {

        response =
                given()
                        .spec(restApiBuilder.requestSpecification())
                        .queryParam("limit", "20")
                        .queryParam("nearestStation", nearestStation)
                        .get(GET_ORDER_LIST);

        return response;
    }
}
