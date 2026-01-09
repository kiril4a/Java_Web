package com.example.Java_Web.controller;

import com.example.Java_Web.domain.model.Product;
import com.example.Java_Web.dto.ProductDTO;
import com.example.Java_Web.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAll() throws Exception {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Cosmic Widget");
        product1.setPrice(99.99);
        product1.setDescription("Amazing product");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Galactic Gadget");
        product2.setPrice(149.99);
        product2.setDescription("Fantastic gadget");

        List<Product> products = Arrays.asList(product1, product2);
        when(productService.findAll()).thenReturn(products);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Cosmic Widget"))
                .andExpect(jsonPath("$[1].name").value("Galactic Gadget"));
    }    @Test
    void testCreate() throws Exception {
        ProductDTO dto = new ProductDTO();
        dto.setName("New Star Product");
        dto.setPrice(199.99);
        dto.setDescription("Brand new cosmic product");

        Product createdProduct = new Product();
        createdProduct.setId(1L);
        createdProduct.setName("New Star Product");
        createdProduct.setPrice(199.99);
        createdProduct.setDescription("Brand new cosmic product");

        when(productService.create(any(Product.class))).thenReturn(createdProduct);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("New Star Product"))
                .andExpect(jsonPath("$.price").value(199.99));
    }

    @Test
    void testGetById_Found() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Cosmic Widget");
        product.setPrice(99.99);
        product.setDescription("Amazing product");

        when(productService.findById(1L)).thenReturn(product);

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Cosmic Widget"))
                .andExpect(jsonPath("$.price").value(99.99));
    }

    @Test
    void testGetById_NotFound() throws Exception {
        when(productService.findById(999L)).thenReturn(null);

        mockMvc.perform(get("/api/products/999"))
                .andExpect(status().isNotFound());
    }    @Test
    void testUpdate() throws Exception {
        ProductDTO dto = new ProductDTO();
        dto.setName("Updated Galaxy Product");dto.setPrice(299.99);
        dto.setDescription("Updated cosmic product");

        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Updated Galaxy Product");
        updatedProduct.setPrice(299.99);
        updatedProduct.setDescription("Updated cosmic product");

        when(productService.update(eq(1L), any(Product.class))).thenReturn(updatedProduct);

        mockMvc.perform(put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Galaxy Product"))
                .andExpect(jsonPath("$.price").value(299.99));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isNoContent());
    }
}
