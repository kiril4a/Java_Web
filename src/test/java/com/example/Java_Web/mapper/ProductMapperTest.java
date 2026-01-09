package com.example.Java_Web.mapper;

import com.example.Java_Web.domain.model.Product;
import com.example.Java_Web.dto.ProductDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    private final ProductMapper mapper = ProductMapper.INSTANCE;

    @Test
    void testToDTO() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Cosmic Widget");
        product.setPrice(99.99);
        product.setDescription("Amazing product");

        ProductDTO dto = mapper.toDTO(product);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Cosmic Widget", dto.getName());
        assertEquals(99.99, dto.getPrice());
        assertEquals("Amazing product", dto.getDescription());
    }

    @Test
    void testToEntity() {
        ProductDTO dto = new ProductDTO();
        dto.setId(2L);
        dto.setName("Galactic Gadget");
        dto.setPrice(149.99);
        dto.setDescription("Fantastic gadget");

        Product product = mapper.toEntity(dto);

        assertNotNull(product);
        assertEquals(2L, product.getId());
        assertEquals("Galactic Gadget", product.getName());
        assertEquals(149.99, product.getPrice());
        assertEquals("Fantastic gadget", product.getDescription());
    }

    @Test
    void testToDTO_NullProduct() {
        ProductDTO dto = mapper.toDTO(null);
        assertNull(dto);
    }

    @Test
    void testToEntity_NullDTO() {
        Product product = mapper.toEntity(null);
        assertNull(product);
    }

    @Test
    void testRoundTrip() {
        Product originalProduct = new Product();
        originalProduct.setId(3L);
        originalProduct.setName("Star Dust");
        originalProduct.setPrice(299.99);
        originalProduct.setDescription("Premium star dust");

        ProductDTO dto = mapper.toDTO(originalProduct);
        Product mappedProduct = mapper.toEntity(dto);

        assertNotNull(mappedProduct);
        assertEquals(originalProduct.getId(), mappedProduct.getId());
        assertEquals(originalProduct.getName(), mappedProduct.getName());
        assertEquals(originalProduct.getPrice(), mappedProduct.getPrice());
        assertEquals(originalProduct.getDescription(), mappedProduct.getDescription());
    }
}
