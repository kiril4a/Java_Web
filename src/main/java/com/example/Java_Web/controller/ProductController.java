package com.example.Java_Web.controller;

import com.example.Java_Web.dto.ProductDTO;
import com.example.Java_Web.mapper.ProductMapper;
import com.example.Java_Web.domain.model.Product;
import com.example.Java_Web.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;
    private final ProductMapper mapper = ProductMapper.INSTANCE;

    public ProductController(ProductService service) { this.service = service; }

    @GetMapping
    public List<ProductDTO> getAll() { return service.findAll().stream().map(mapper::toDTO).collect(Collectors.toList()); }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto) {
        Product product = mapper.toEntity(dto);
        Product created = service.create(product);
        return ResponseEntity.status(201).body(mapper.toDTO(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        Product product = service.findById(id);
        if (product == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(mapper.toDTO(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
        Product product = mapper.toEntity(dto);
        Product updated = service.update(id, product);
        return ResponseEntity.ok(mapper.toDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
