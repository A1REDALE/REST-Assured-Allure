package courier;
import config.Config;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class CourierClient {
    private final String CREATE_COURIER = "/api/v1/courier";
    private final String COURIER_ID = "/api/v1/courier/{courierId}";
    private final String LOGIN = "/api/v1/courier/login";

    protected RequestSpecification getSpec(){
        return given().log().all()
                .header("Content-type", "application/json")
                .baseUri(Config.BASE_URL);
    }
    public ValidatableResponse createCourier(Courier courier){
         return
                 getSpec()
                         .body(courier)
                         .when()
                         .post(CREATE_COURIER)
                         .then().log().all()
                         .assertThat();
    }
    public ValidatableResponse login(CourierCredentials creds) {

        return getSpec()
                .body(creds)
                .when()
                .post(LOGIN)
                .then().log().all()
                .assertThat();
    }
    public void delete(int courierId) { 
        getSpec()
                .pathParam("courierId", courierId)
                .when()
                .delete(COURIER_ID)
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }
}
