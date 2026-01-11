package com.example.Java_Web.service;

import com.example.Java_Web.AbstractIntegrationTest;
import com.example.Java_Web.domain.model.Order;
import com.example.Java_Web.domain.model.OrderItem;
import com.example.Java_Web.domain.model.Product;
import com.example.Java_Web.domain.model.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
public class OrderServiceTest extends AbstractIntegrationTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Test
    void testSaveAndGetOrder() {
        Category category = new Category();
        category.setName("Books");
        categoryService.save(category);

        Product product = new Product();
        product.setName("The Lord of the Rings");
        product.setPrice(new BigDecimal("25.99"));
        product.setCategory(category);
        productService.create(product);

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(1);
        orderItem.setPrice(product.getPrice());

        Order order = new Order();
        order.setCustomerEmail("test@example.com");
        order.addItem(orderItem);

        orderService.save(order);

        Order found = orderService.findAll().stream().findFirst().orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getCustomerEmail()).isEqualTo("test@example.com");
        assertThat(found.getItems()).hasSize(1);
        assertThat(found.getItems().get(0).getProduct().getName()).isEqualTo("The Lord of the Rings");
    }
}
