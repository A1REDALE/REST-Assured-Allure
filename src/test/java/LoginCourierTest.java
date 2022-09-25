import courier.CourierClient;
import courier.Courier;
import courier.CourierCredentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotEquals;

public class LoginCourierTest extends CourierClient {
    int courierId;
    Courier courier;

    @Before
    public void setUp(){
        courier = Courier.getRandomCourier();
    }

    @After
    public void tearDown(){
        if(courierId != 0){
            delete(courierId);
        }
    }

    @Test
    public void courierCanLogIn(){
        createCourier(courier);
        CourierCredentials creds = CourierCredentials.from(courier);
        courierId = login(creds)
                .statusCode(200)
                .extract().path("id");
        assertNotEquals(0, courierId);
    }

    @Test
    public void courierCantLogInWithoutLogin(){
        createCourier(courier);
        CourierCredentials creds = CourierCredentials.getCredentialsWithoutLogin(courier);
        login(creds)
                .statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    public void courierCantLogInWithoutPassword() {
        createCourier(courier);
        CourierCredentials creds = CourierCredentials.getCredentialWithoutPassword(courier);
        login(creds)
                .statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    public void courierCantLogInWithoutRequiredFields() {
        createCourier(courier);
        CourierCredentials creds = CourierCredentials.getCredentialWithoutRequiredFields(courier);
        login(creds)
                .statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    public void courierCantLogInWithWrongLogin(){
        createCourier(courier);
        CourierCredentials creds = CourierCredentials.getCredentialWithWrongLogin(courier);
        login(creds)
                .statusCode(404)
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    public void courierCantLogInWithWrongPassword(){
        createCourier(courier);
        CourierCredentials creds = CourierCredentials.getCredentialWithWrongPassword(courier);
        login(creds)
                .statusCode(404)
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    public void authorizationWithDoesNotExistUser(){
        CourierCredentials creds = CourierCredentials.getCredentialNotExistUser(courier);
        login(creds)
                .statusCode(404)
                .assertThat().body("message", equalTo("Учетная запись не найдена"));
    }
}
