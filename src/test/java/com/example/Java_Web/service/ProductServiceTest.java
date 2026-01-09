package com.example.Java_Web.service;

import com.example.Java_Web.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService();
    }

    @Test
    void testFindAll_WhenEmpty() {
        List<Product> products = productService.findAll();
        assertNotNull(products);
        assertTrue(products.isEmpty());
    }

    @Test
    void testCreate() {
        Product product = new Product();
        product.setName("Cosmic Widget");
        product.setPrice(99.99);

        Product created = productService.create(product);

        assertNotNull(created);
        assertNotNull(created.getId());
        assertEquals("Cosmic Widget", created.getName());
        assertEquals(99.99, created.getPrice());
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(50.0);

        Product created = productService.create(product);
        Product found = productService.findById(created.getId());

        assertNotNull(found);
        assertEquals(created.getId(), found.getId());
        assertEquals("Test Product", found.getName());
    }

    @Test
    void testFindById_NotFound() {
        Product found = productService.findById(999L);
        assertNull(found);
    }

    @Test
    void testUpdate() {
        Product product = new Product();
        product.setName("Original Name");
        product.setPrice(100.0);

        Product created = productService.create(product);
        Long id = created.getId();

        Product updated = new Product();
        updated.setName("Updated Name");
        updated.setPrice(150.0);

        Product result = productService.update(id, updated);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Updated Name", result.getName());
        assertEquals(150.0, result.getPrice());
    }

    @Test
    void testDelete() {
        Product product = new Product();
        product.setName("To Delete");
        product.setPrice(75.0);

        Product created = productService.create(product);
        Long id = created.getId();

        assertNotNull(productService.findById(id));

        productService.delete(id);

        assertNull(productService.findById(id));
    }

    @Test
    void testFindAll_WithMultipleProducts() {
        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setPrice(10.0);

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setPrice(20.0);

        productService.create(product1);
        productService.create(product2);

        List<Product> products = productService.findAll();

        assertEquals(2, products.size());
    }
}
