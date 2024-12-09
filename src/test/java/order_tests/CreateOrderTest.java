package order_tests;

import base_test.api_base.OrderApi;
import base_test.data.OrderData;
import base_test.data.OrderDataGenerate;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private final OrderData orderData;
    private final String color;

    public CreateOrderTest(OrderData orderData, String color) {
        this.orderData = orderData;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] orderTestData() {
        // Генерация случайных данных для теста

        OrderData orderDataNoColour = new OrderData();
        orderDataNoColour.setFirstName(OrderDataGenerate.generateFirstName());
        orderDataNoColour.setLastName(OrderDataGenerate.generateLastName());
        orderDataNoColour.setAddress(OrderDataGenerate.generateAddress());
        orderDataNoColour.setMetroStation(String.valueOf(OrderDataGenerate.generatMemetroStation()));
        orderDataNoColour.setPhone(OrderDataGenerate.generatePhone());
        orderDataNoColour.setRentTime(String.valueOf(OrderDataGenerate.generateRentTime()));
        orderDataNoColour.setDeliveryDate(OrderDataGenerate.generateDeliveryDate());
        orderDataNoColour.setComment(OrderDataGenerate.generateComment());

        OrderData orderDataBlack = new OrderData();
        orderDataBlack.setFirstName(OrderDataGenerate.generateFirstName());
        orderDataBlack.setLastName(OrderDataGenerate.generateLastName());
        orderDataBlack.setAddress(OrderDataGenerate.generateAddress());
        orderDataBlack.setMetroStation(String.valueOf(OrderDataGenerate.generatMemetroStation()));
        orderDataBlack.setPhone(OrderDataGenerate.generatePhone());
        orderDataBlack.setRentTime(String.valueOf(OrderDataGenerate.generateRentTime()));
        orderDataBlack.setDeliveryDate(OrderDataGenerate.generateDeliveryDate());
        orderDataBlack.setComment(OrderDataGenerate.generateComment());

        OrderData orderDataGrey = new OrderData();
        orderDataGrey.setFirstName(OrderDataGenerate.generateFirstName());
        orderDataGrey.setLastName(OrderDataGenerate.generateLastName());
        orderDataGrey.setAddress(OrderDataGenerate.generateAddress());
        orderDataGrey.setMetroStation(String.valueOf(OrderDataGenerate.generatMemetroStation()));
        orderDataGrey.setPhone(OrderDataGenerate.generatePhone());
        orderDataGrey.setRentTime(String.valueOf(OrderDataGenerate.generateRentTime()));
        orderDataGrey.setDeliveryDate(OrderDataGenerate.generateDeliveryDate());
        orderDataGrey.setComment(OrderDataGenerate.generateComment());

        OrderData orderDataBlackOrGrey = new OrderData();
        orderDataBlackOrGrey.setFirstName(OrderDataGenerate.generateFirstName());
        orderDataBlackOrGrey.setLastName(OrderDataGenerate.generateLastName());
        orderDataBlackOrGrey.setAddress(OrderDataGenerate.generateAddress());
        orderDataBlackOrGrey.setMetroStation(String.valueOf(OrderDataGenerate.generatMemetroStation()));
        orderDataBlackOrGrey.setPhone(OrderDataGenerate.generatePhone());
        orderDataBlackOrGrey.setRentTime(String.valueOf(OrderDataGenerate.generateRentTime()));
        orderDataBlackOrGrey.setDeliveryDate(OrderDataGenerate.generateDeliveryDate());
        orderDataBlackOrGrey.setComment(OrderDataGenerate.generateComment());

        return new Object[][] {
                { orderDataNoColour, "" },
                { orderDataBlack, "BLACK" },
                { orderDataGrey, "GREY" },
                { orderDataBlackOrGrey, "BLACK_OR_GREY" }
        };
    }

    @DisplayName("Создание заказа с разеыми цветами: BLACK, GREY, BLACK_OR_GREY")
    @Description("Проверяем код ответа создания курьера 201 и содержание body ответа \"track\": непустое")
    @Test
    public void createOrderTest() {
        OrderApi orderApi = new OrderApi();
        orderData.setColor(color);

        Response response = orderApi.createOneOrder(orderData);

        assertThat("Код ответа должен быть 201, пришел " + response.getStatusCode(), response.getStatusCode(), equalTo(HttpStatus.SC_CREATED));
        assertThat("В теле ответа должно прийти \"track\": непустое, пришло \"track\": " + response.jsonPath().getString("track"), response.jsonPath().getString("track"), notNullValue());
    }
}


