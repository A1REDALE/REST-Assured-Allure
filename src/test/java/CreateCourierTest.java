import courier.CourierClient;
import courier.Courier;
import courier.CourierCredentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class CreateCourierTest {
    CourierClient courierClient;
    private boolean isOk;
    private Courier courier;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
        if (isOk == true) {
            CourierCredentials creds = CourierCredentials.from(courier);
            int courierId = courierClient.login(creds).extract().path("id");
            courierClient.delete(courierId);
        }

    }

    @Test
    public void checkCreateNewCourier() {
        courier = Courier.getRandomCourier();
        isOk = courierClient.createCourier(courier)
                .statusCode(201)
                .extract().path("ok");
        assertTrue(isOk);
    }

    @Test
    public void cantCreateTwoIdenticalCouriers() {
        courier = new Courier("ninja1469", "1234", "saskefwfefwf");
        courierClient.createCourier(courier);
        courierClient.createCourier(courier)
                .statusCode(409)
                .assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    public void checkCreateCourierWithoutLogin() {
        courier = Courier.getCourierWithoutLogin();
        courierClient.createCourier(courier)
                .statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    public void checkCreateCourierWithoutPassword() {
        courier = Courier.getCourierWithoutPassword();
        courierClient.createCourier(courier)
                .statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    public void checkCreateCourierWithoutRequiredFields() {
        courier = Courier.getCourierWithoutRequiredFields();
        courierClient.createCourier(courier)
                .statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}

