package apiTraining;

import models.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class ApiTestLifeCycleOfRequest {

    @Test
    public void createProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/create.php";
        Product product = new Product(
                "Sweatband",
                "This is a Nike wrist sweatband.",
                5,
                3
        );
        var response = given().body(product).when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void updateProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/update.php";
        Product product = new Product(
                21,
                "Sweatband",
                "This is a Nike wrist sweatband.",
                6,
                3,
                "Unisex products."
        );
        var response = given().body(product).when().put(endpoint).then();
        response.log().body();
    }

    @Test
    public void getProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/read_one.php";
        var response = given().queryParam("id", 21).when().get(endpoint).then();
        response.log().body();
    }

    @Test
    public void deleteProduct() {
        String endpoint = "http://localhost:8888/api_testing/product/delete.php";
        String body = """
                {
                "id": 21
                }
                """;
        var response = given().body(body).when().delete(endpoint).then();
        response.log().body();
    }

}
