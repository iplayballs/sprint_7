package base_test.constants;

import lombok.Setter;

public class UrlConstants {
    @Setter
    private static String id;

    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";

    public static final String COURIER_LOGIN = "/api/v1/courier/login";
    public static final String COURIER_CREATE = "/api/v1/courier";
    public static final String COURIER_DELETE = "/api/v1/courier/" + id;
    public static final String COURIER_ORDER_LIST = "/api/v1/orders" + id;
    public static final String ORDER_CREATE = "/api/v1/orders";
    public static final String GET_ORDER_LIST = "/api/v1/orders/";
}


