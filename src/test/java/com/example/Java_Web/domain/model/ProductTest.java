package com.example.Java_Web.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testProductGettersAndSetters() {
        Product product = new Product();
        
        product.setId(1L);
        product.setName("Cosmic Widget");
        product.setPrice(99.99);
        product.setDescription("A wonderful cosmic product");

        assertEquals(1L, product.getId());
        assertEquals("Cosmic Widget", product.getName());
        assertEquals(99.99, product.getPrice());
        assertEquals("A wonderful cosmic product", product.getDescription());
    }

    @Test
    void testProductIdCanBeNull() {
        Product product = new Product();
        assertNull(product.getId());
    }

    @Test
    void testProductPriceCanBeSet() {
        Product product = new Product();
        product.setPrice(150.50);
        assertEquals(150.50, product.getPrice());
    }
}
