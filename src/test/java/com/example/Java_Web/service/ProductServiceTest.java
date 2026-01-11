package com.example.Java_Web.service;

import com.example.Java_Web.AbstractIntegrationTest;
import com.example.Java_Web.domain.model.Category;
import com.example.Java_Web.domain.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
public class ProductServiceTest extends AbstractIntegrationTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Test
    void testSaveAndGetProduct() {
        Category category = new Category();
        category.setName("Electronics");
        categoryService.save(category);

        Product product = new Product();
        product.setName("Laptop");
        product.setPrice(new BigDecimal("1200.00"));
        product.setCategory(category);

        productService.create(product);

        Product found = productService.findById(product.getId());
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Laptop");
    }
}
