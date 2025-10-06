package com.example.Java_Web.service;

import com.example.Java_Web.domain.model.Product;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProductService {

    private final Map<Long, Product> db = new HashMap<>();
    private long idCounter = 1;

    public List<Product> findAll() { return new ArrayList<>(db.values()); }
    public Product findById(Long id) { return db.get(id); }
    public Product create(Product product) { product.setId(idCounter++); db.put(product.getId(), product); return product; }
    public Product update(Long id, Product product) { product.setId(id); db.put(id, product); return product; }
    public void delete(Long id) { db.remove(id); }
}
