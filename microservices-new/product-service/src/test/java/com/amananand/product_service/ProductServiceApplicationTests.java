package com.amananand.product_service;

import com.amananand.product_service.dto.ProductRequest;
import com.amananand.product_service.dto.ProductResponse;
import com.amananand.product_service.repository.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.4");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    void shouldCreateProduct() throws Exception {
        ProductRequest productRequest = getProductRequest();
        String productRequestStr = objectMapper.writeValueAsString(productRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productRequestStr))
                .andExpect(status().isCreated());
        Assertions.assertFalse(productRepository.findAll().isEmpty());
    }

    @Test
    void shouldGetProduct() throws Exception {

        String productRequestStr = objectMapper.writeValueAsString(getProductRequest());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productRequestStr))
                .andExpect(status().isCreated());

        String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/api/product"))
                .andExpect(status().isOk())
                .andReturn() // return like actual api call
                .getResponse()
                .getContentAsString();
        List<ProductResponse> productResponses = objectMapper.readValue(responseString, new TypeReference<List<ProductResponse>>() {
        });
        Assertions.assertFalse(productRepository.findAll().isEmpty());
        Assertions.assertEquals(productResponses.get(0).getName(), "iPhone 13");
        Assertions.assertEquals(productResponses.get(0).getDescription(), "iPhone 13 desc");
        Assertions.assertEquals(productResponses.get(0).getPrice(), BigDecimal.valueOf(88000));
    }

    private ProductRequest getProductRequest() {
        return ProductRequest.builder()
                .name("iPhone 13")
                .description("iPhone 13 desc")
                .price(BigDecimal.valueOf(88000))
                .build();
    }

}
