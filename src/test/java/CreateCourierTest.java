import courier.CourierClient;
import courier.Courier;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class CreateCourierTest extends CourierClient {

    @Test
   public  void checkCreateNewCourier(){
        Courier courier = Courier.getRandomCourier();
        boolean isOk = createCourier(courier)
                .statusCode(201)
                .extract().path("ok");
                assertTrue(isOk);
    }

    @Test
    public void cantCreateTwoIdenticalCouriers(){
        Courier courier = new Courier("ninja1469", "1234", "saskefwfefwf");
        createCourier(courier);
        createCourier(courier)
                .statusCode(409)
                .assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    public void checkCreateCourierWithoutLogin(){
        Courier courier = Courier.getCourierWithoutLogin();
        createCourier(courier)
                .statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    public void checkCreateCourierWithoutPassword(){
        Courier courier = Courier.getCourierWithoutPassword();
        createCourier(courier)
                .statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    public void checkCreateCourierWithoutRequiredFields(){
        Courier courier = Courier.getCourierWithoutRequiredFields();
        createCourier(courier)
                .statusCode(400)
                .assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }
}










//"Недостаточно данных для создания учетной записи"


    //assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
//Courier courier = new Courier("ninja1457", "1234", "saskefwfefwf");
//String json = "{\"login\": \"ninja1334446\", \"password\": \"1234\", \"firstName\": \"saskefwfefwf\"}";