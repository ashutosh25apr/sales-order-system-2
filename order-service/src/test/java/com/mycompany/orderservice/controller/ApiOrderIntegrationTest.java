package com.mycompany.orderservice.controller;

import com.mycompany.orderservice.JwtTestUtils;
import com.mycompany.orderservice.entity.Order;
import com.mycompany.orderservice.repository.OrderRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Testcontainers
public class ApiOrderIntegrationTest {

    @Container
    static MongoDBContainer mongoDbContainer = new MongoDBContainer("mongo:6.0.4")
        .withStartupAttempts(3)
        .withStartupTimeout(Duration.ofSeconds(120))
        .withCreateContainerCmdModifier(cmd -> cmd.withPlatform("linux/amd64"));

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDbContainer::getReplicaSetUrl);
        registry.add("jwt.secret", () -> jwtSecretStatic);
    }

    @Autowired
    private OrderRepository orderRepository;

    private static String jwtSecretStatic = "VopbmTjbcrnKD+kAHH4imP1HdlyXu6A+CfdleXyLnQ0=";

    @Test
    public void testCreateOrder() {
        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .headers("Authorization", "Bearer " + JwtTestUtils.getJwtRequestHeader(jwtSecretStatic))
                    .body(new HashMap<String, Object>(){
                        {
                            put("customerId", "1");
                            put("items", Arrays.asList("Banana", "Pears"));
                        }
                    })
                    .log().all()
                .when()
                    .post("http://localhost:8082/order")
                .then()
                    .log().all()
                    .assertThat()
                    .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void testGetOrders() {

        orderRepository.saveAll(Arrays.asList(
                order("1", Arrays.asList("Banana", "Oranges", "Apples")),
                order("2", Arrays.asList("iPhone 8", "iPad 4 Mini", "MacBook Pro")),
                order("3", Arrays.asList("Pen", "Highlighter", "Cups"))
        ));

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .headers("Authorization", "Bearer " + JwtTestUtils.getJwtRequestHeader(jwtSecretStatic))
                    .log().all()
                .when()
                    .get("http://localhost:8082/orders/list")
                .then()
                    .log().all()
                    .assertThat()
                    .statusCode(HttpStatus.OK.value())
                    .body("size()", equalTo(3));
    }

    @Test
    public void testGetOrdersForCustomer() {
        orderRepository.save(order("1", Arrays.asList("Book", "Notepad")));

        RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .headers("Authorization", "Bearer " + JwtTestUtils.getJwtRequestHeader(jwtSecretStatic))
                    .log().all()
                .when()
                    .get("http://localhost:8082/orders?customerId=1")
                .then()
                    .log().all()
                    .assertThat()
                    .statusCode(HttpStatus.OK.value())
                    .body("size()", equalTo(1));
    }

    private Order order(String customerId, List<String> items){
        Order order = new Order();
        order.setCustomerId(customerId);
        order.setItems(items);
        return order;
    }

}
