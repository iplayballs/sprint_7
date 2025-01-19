package order_tests;

import base_test.api_base.OrderApi;
import base_test.data.OrderData;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static base_test.data.OrderData.Color.*;
import static base_test.data.OrderDataGenerate.createOrderData;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private final OrderData orderData;


    public CreateOrderTest(OrderData orderData) {
        this.orderData = orderData;
    }

    @Parameterized.Parameters
    public static Object[][] orderTestData() {
        // Генерация случайных данных для теста

        OrderData orderDataNoColour = createOrderData(NO_COLOR);
        OrderData orderDataBlack = createOrderData(BLACK);
        OrderData orderDataGrey = createOrderData(GREY);
        OrderData orderDataBlackOrGrey = createOrderData(BLACK_OR_GREY);

        return new Object[][] {
                { orderDataNoColour },
                { orderDataBlack },
                { orderDataGrey },
                { orderDataBlackOrGrey }
            };
    }

    @DisplayName("Создание заказа с разеыми цветами: BLACK, GREY, BLACK_OR_GREY")
    @Description("Проверяем код ответа создания курьера 201 и содержание body ответа \"track\": непустое")
    @Test
    public void createOrderTest() {
        OrderApi orderApi = new OrderApi();

        Response response = orderApi.createOneOrder(orderData);

        assertThat("Код ответа должен быть 201, пришел " + response.getStatusCode(), response.getStatusCode(), equalTo(HttpStatus.SC_CREATED));
        assertThat("В теле ответа должно прийти \"track\": непустое, пришло \"track\": " + response.jsonPath().getString("track"), response.jsonPath().getString("track"), notNullValue());
    }
}


