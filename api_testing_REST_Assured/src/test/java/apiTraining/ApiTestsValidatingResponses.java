package apiTraining;

import models.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ApiTestsValidatingResponses {

    @Test
    public void getProductAndVerifyItResponseBody() {
        String endpoint = "http://localhost:8888/api_testing/product/read_one.php";
        given().queryParam("id", 1).when().get(endpoint).then()
                .assertThat().statusCode(200)
                .body("id", equalTo("1"))
                .body("name", equalTo("Bamboo Thermal Ski Coat"))
                .body("description", equalTo("You’ll be the most environmentally conscious skier on the slopes – and the most stylish – wearing our fitted bamboo thermal ski coat, made from organic bamboo with recycled plastic down filling."))
                .body("price", equalTo("99.00"))
                .body("category_id", equalTo("2"))
                .body("category_name", equalTo("Active Wear - Women"));
//                log().body(); // when this is added after then(), it will displayed the response body after the test pass.
    }

    @Test
    public void getProductAndVerifyItsComplexResponseBody() {
        String endpoint = "http://localhost:8888/api_testing/product/read.php";
        given().when().get(endpoint).then().log().body()
                .assertThat().statusCode(200)
                .body("records.size()", greaterThan(0))
                .body("records.id", everyItem(notNullValue()))
                .body("records.name", everyItem(notNullValue()))
                .body("records.description", everyItem(notNullValue()))
                .body("records.price", everyItem(notNullValue()))
                .body("records.category_id", everyItem(notNullValue()))
                .body("records.category_name", everyItem(notNullValue()))
                .body("records.id[0]", equalTo("20"));
    }

    @Test
    public void getProductAndVerifyItsHeaders() {
        String endpoint = "http://localhost:8888/api_testing/product/read.php";
        given().when().get(endpoint).then().log().headers()
                .assertThat().statusCode(200)
                .header("Content-Type", equalTo("application/json; charset=UTF-8"));

    }

    @Test
    public void getDeserializedProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/read_one.php";

        Product expectedProduct = new Product(
                2,
                "Cross-Back Training Tank",
                "The most awesome phone of 2013!",
                299.00,
                2,
                "Active Wear - Women"
        );
        Product actualProduct = given().queryParam("id", "2")
                .when().get(endpoint)
                .as(Product.class);

        assertThat(actualProduct, samePropertyValuesAs(expectedProduct)); // sameProperty... is used to assert to objects and their values
    }

}
