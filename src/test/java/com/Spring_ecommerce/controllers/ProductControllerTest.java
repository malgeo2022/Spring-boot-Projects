package com.Spring_ecommerce.controllers;

import com.Spring_ecommerce.service.product.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {


    private final static String CONTENT_TYPE = "application/json";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ProductService productService;

    @Test
    void add() {
    }

    @Test
    void getById() {
    }

    @Test
    void getAll() {
    }

    @Test
    void getByProductName() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void getDto() {
    }

    @Test
    void getCart() {
    }
}