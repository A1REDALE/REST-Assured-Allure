import courier.CourierClient;
import courier.Courier;
import courier.CourierCredentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotEquals;

public class LoginCourierTest {
    CourierClient courierClient;
    int courierId;
    Courier courier;


    @Before
    public void setUp() {
        courierClient = new CourierClient();
        courier = Courier.getRandomCourier();
    }

    @After
    public void tearDown() {
        if (courierId != 0) {
            courierClient.delete(courierId);
        }
    }

    @Test
    public void courierCanLogIn() {
        courierClient.createCourier(courier);
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = courierClient.login(creds)
                .statusCode(200)
                .extract().path("id");
        assertNotEquals(0, courierId);
    }

    @Test
    public void courierCantLogInWithoutLogin() {
        courierClient.createCourier(courier);
        CourierCredentials creds = CourierCredentials.getCredentialsWithoutLogin(courier);
        courierClient.login(creds)
                .statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
        CourierCredentials serviceCreds = CourierCredentials.from(courier);
        courierId = courierClient.login(serviceCreds)
                .extract().path("id");

    }

    @Test
    public void courierCantLogInWithoutPassword() {
        courierClient.createCourier(courier);
        CourierCredentials creds = CourierCredentials.getCredentialWithoutPassword(courier);
        courierClient.login(creds)
                .statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
        CourierCredentials serviceCreds = CourierCredentials.from(courier);
        courierId = courierClient.login(serviceCreds)
                .extract().path("id");
    }

    @Test
    public void courierCantLogInWithoutRequiredFields() {
        courierClient.createCourier(courier);
        CourierCredentials creds = CourierCredentials.getCredentialWithoutRequiredFields(courier);
        courierClient.login(creds)
                .statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
        CourierCredentials serviceCreds = CourierCredentials.from(courier);
        courierId = courierClient.login(serviceCreds)
                .extract().path("id");
    }

    @Test
    public void courierCantLogInWithWrongLogin() {
        courierClient.createCourier(courier);
        CourierCredentials creds = CourierCredentials.getCredentialWithWrongLogin(courier);
        courierClient.login(creds)
                .statusCode(404)
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
        CourierCredentials serviceCreds = CourierCredentials.from(courier);
        courierId = courierClient.login(serviceCreds)
                .extract().path("id");
    }

    @Test
    public void courierCantLogInWithWrongPassword() {
        courierClient.createCourier(courier);
        CourierCredentials creds = CourierCredentials.getCredentialWithWrongPassword(courier);
        courierClient.login(creds)
                .statusCode(404)
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
        CourierCredentials serviceCreds = CourierCredentials.from(courier);
        courierId = courierClient.login(serviceCreds)
                .extract().path("id");
    }

    @Test
    public void authorizationWithDoesNotExistUser() {
        CourierCredentials creds = CourierCredentials.getCredentialNotExistUser(courier);
        courierClient.login(creds)
                .statusCode(404)
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }
}
