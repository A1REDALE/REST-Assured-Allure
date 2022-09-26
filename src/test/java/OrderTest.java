import order.Order;
import order.OrderClient;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;


@RunWith(Parameterized.class)
public class OrderTest extends OrderClient {

    private final Order checkedOrder;
    private int track;

    public OrderTest(Order checkedOrder) {
        this.checkedOrder = checkedOrder;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {Order.getOrderWithBlackScooter()},
                {Order.getOrderWithGreyScooter()},
                {Order.getOrderWithBothColors()},
                {Order.getOrderWithoutScooterColor()},
        };
    }

    @After
    public void tearDown() {
        if (track != 0) {
            orderCancel(track);
        }
    }

    @Test
    public void createOrder() {
        Order order = checkedOrder;
        track = orderCreate(order)
                .statusCode(201)
                .extract().path("track");
        assertNotEquals(0, track);
    }
}
