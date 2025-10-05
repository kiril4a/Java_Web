package com.example.java_web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.example.java_web.dto.ProductDTO;
import com.example.java_web.domain.model.Product;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toDTO(Product product);
    Product toEntity(ProductDTO dto);
}
