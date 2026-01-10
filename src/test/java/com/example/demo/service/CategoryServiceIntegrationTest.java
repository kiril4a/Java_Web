package com.example.demo.service;

import com.example.demo.BaseIntegrationTest;
import com.example.demo.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
class CategoryServiceIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void testCreateAndGetCategory() {
        Category category = new Category();
        category.setName("Test Category");

        Category createdCategory = categoryService.createCategory(category);
        assertThat(createdCategory).isNotNull();
        assertThat(createdCategory.getId()).isNotNull();
        assertThat(createdCategory.getName()).isEqualTo("Test Category");

        Category foundCategory = categoryService.getAllCategories().get(0);
        assertThat(foundCategory).isNotNull();
        assertThat(foundCategory.getName()).isEqualTo("Test Category");
    }
}
