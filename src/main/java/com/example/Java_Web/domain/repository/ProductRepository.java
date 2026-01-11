package com.example.Java_Web.domain.repository;

import com.example.Java_Web.domain.model.Product;
import com.example.Java_Web.dto.ProductNameAndPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId ORDER BY p.name")
    List<Product> findByCategoryIdOrderByName(Long categoryId);

    @Query("SELECT p.name as name, p.price as price FROM Product p WHERE p.price > :price")
    List<ProductNameAndPrice> findProductsWithPriceGreaterThan(Double price);
}
