package base_test.api_base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static base_test.constants.UrlConstants.BASE_URL;

public class RestApiBuilder {

    protected RequestSpecification requestSpecification(){
        return new RequestSpecBuilder()
                //задаем базовый урл для всех запросов
                .setBaseUri(BASE_URL)
                //устанавливаем хедер для всех запросов
                .setContentType(ContentType.JSON)
                //завершаем настройку RequestSpecBuilder
                .build()
                //добавляем логирование запроса и ответа в Allure отчет
                .filter(new AllureRestAssured())
                //добавляем логирование запроса и ответа в консоль для отладки
                .log().all();
    }
}
