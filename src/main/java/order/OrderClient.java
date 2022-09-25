package order;
import config.Config;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class OrderClient{
    protected final String CREATE_ORDER = "/api/v1/orders";
    protected final String CANCEL_ORDER = "/api/v1/orders/cancel";
    int track;

    protected RequestSpecification getSpec(){
        return given().log().all()
                .header("Content-type", "application/json")
                .baseUri(Config.BASE_URL);
    }
    public ValidatableResponse orderCreate(Order order){
        return
                getSpec()
                        .body(order)
                        .when()
                        .post(CREATE_ORDER)
                        .then().log().all()
                        .assertThat();
    }
    public ValidatableResponse orderCancel(int track) {

        return getSpec()
                .queryParam("track",track)
                .when()
                .put(CANCEL_ORDER)
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }
}
