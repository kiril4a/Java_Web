package com.example.Java_Web.service;

import com.example.Java_Web.AbstractIntegrationTest;
import com.example.Java_Web.domain.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
public class CategoryServiceTest extends AbstractIntegrationTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void testSaveAndGetCategory() {
        Category category = new Category();
        category.setName("New Category");

        categoryService.save(category);

        Category found = categoryService.findAll().stream()
                .filter(c -> c.getName().equals("New Category"))
                .findFirst()
                .orElse(null);

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("New Category");
    }
}
