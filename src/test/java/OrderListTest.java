import order.Order;
import order.OrderClient;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class OrderListTest {
    OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    public void listOfOrdersShouldReturnToResponseBody() {

        List<Order> orderList = orderClient.getOrderList().then().statusCode(200).extract().body().path("orders");
        boolean isOk = orderList.size() > 0;
        assertTrue(isOk);
    }
}
