package com.kuryaevao.test;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DemowebshopTests {

    String emailToSubscribe = "aleksandr.kuryaev@simbirsoft.com";

    @Test
    void subscribeNewsletterTest() {
        Response response =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .body("email="+emailToSubscribe)
                        .when()
                        .post("https://demowebshop.tricentis.com/subscribenewsletter")
                        .then()
                        .statusCode(200)
                        .body("Success", is(true))
                        .body("Result", is("Thank you for signing up! A verification email has been sent. We appreciate your interest."))
                        .extract().response();

        System.out.println("Result: " + response.path("Result"));
    }

    @Test
    void addToWishlistTest() {
        Response response =
                given()
                        .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                        .body("addtocart_14.EnteredQuantity=1")
                        .when()
                        .post("https://demowebshop.tricentis.com/addproducttocart/details/14/2")
                        .then()
                        .statusCode(200)
                        .body("success", is(true))
                        .body("message", is("The product has been added to your <a href=\"/wishlist\">wishlist</a>"))
                        .body("updatetopwishlistsectionhtml", is("(1)"))
                        .extract().response();

        System.out.println("Response: " + response.path("updatetopwishlistsectionhtml"));
    }
}