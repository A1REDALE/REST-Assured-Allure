import courier.CourierClient;
import courier.Courier;
import courier.CourierCredentials;
import org.junit.After;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class CreateCourierTest extends CourierClient {
    private boolean isOk;
    private Courier courier;

    @After
    public void tearDown() {
        if (isOk == true) {
            CourierCredentials creds = CourierCredentials.from(courier);
            int courierId = login(creds).extract().path("id");
            delete(courierId);
        }

    }

    @Test
    public void checkCreateNewCourier() {
        courier = Courier.getRandomCourier();
        isOk = createCourier(courier)
                .statusCode(201)
                .extract().path("ok");
        assertTrue(isOk);
    }

    @Test
    public void cantCreateTwoIdenticalCouriers() {
        courier = new Courier("ninja1469", "1234", "saskefwfefwf");
        createCourier(courier);
        createCourier(courier)
                .statusCode(409)
                .assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    public void checkCreateCourierWithoutLogin() {
        courier = Courier.getCourierWithoutLogin();
        createCourier(courier)
                .statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    public void checkCreateCourierWithoutPassword() {
        courier = Courier.getCourierWithoutPassword();
        createCourier(courier)
                .statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    public void checkCreateCourierWithoutRequiredFields() {
        courier = Courier.getCourierWithoutRequiredFields();
        createCourier(courier)
                .statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}

