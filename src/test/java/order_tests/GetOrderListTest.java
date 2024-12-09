package order_tests;

import base_test.api_base.OrderApi;
import base_test.data.OrderDataGenerate;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;

public class GetOrderListTest {

    @DisplayName("Получение списка заказов из 1 заказа")
    @Description("Проверяем код ответа получения списка заказов 200 и содержание body ответа 1 заказ")
    @Test
    public void testGetOrderListLimitOne() {

        OrderApi orderApi = new OrderApi();

        Response response = orderApi.getOrderListLimit(1);

        assertThat("Код ответа должен быть 200, пришел " + response.getStatusCode(), response.getStatusCode(), equalTo(HttpStatus.SC_OK));
        assertThat("В теле ответа должен быть 1 объект заказа, но пришло: " + response.jsonPath().getList("orders").size(), response.jsonPath().getList("orders").size(), equalTo(1));
    }

    @DisplayName("Получение списка заказов из 30 заказа")
    @Description("Проверяем код ответа получения списка заказов 200 и содержание body ответа 30 заказ")
    @Test
    public void testGetOrderListLimitThirty() {

        OrderApi orderApi = new OrderApi();

        Response response = orderApi.getOrderListLimit(30);

        assertThat("Код ответа должен быть 200, пришел " + response.getStatusCode(), response.getStatusCode(), equalTo(HttpStatus.SC_OK));
        assertThat("В теле ответа должен быть 1 объект заказа, но пришло: " + response.jsonPath().getList("orders").size(), response.jsonPath().getList("orders").size(), equalTo(30));
    }

    @DisplayName("Получение списка заказов c одинаковой metroStation")
    @Description("Проверяем код ответа получения списка заказов 200 и содержание body ответа c одинаковой metroStation")
    @Test
    public void testGetOrderListLimitThirtyAndOneStation() {

        OrderApi orderApi = new OrderApi();

        String metroStation = String.valueOf(OrderDataGenerate.generatMemetroStation());
        Response response = orderApi.getOrderListStation(metroStation);

        assertThat("Код ответа должен быть 200, пришел " + response.getStatusCode(), response.getStatusCode(), equalTo(HttpStatus.SC_OK));
        List<String> metroStationsResponseList = response.jsonPath().getList("orders.metroStation");
        if (metroStationsResponseList != null){
        assertThat("В теле ответа во всех заказах должно быть в metroStation значение: " + metroStation +  ", но пришло: ", metroStationsResponseList, everyItem(equalTo(metroStation)));
        }
    }
}
