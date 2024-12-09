package base_test;

import base_test.api_base.CourierApi;
import base_test.data.CourierData;
import org.junit.After;

public abstract class BaseTestAfterCourier {

    protected CourierData courierData;
    protected CourierApi courierApi;


    @After
    public void deleteCourierAfterTest() {
        courierApi.deleteCourier(courierData);
    }
}
