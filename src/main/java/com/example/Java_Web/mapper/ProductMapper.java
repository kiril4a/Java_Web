package com.example.Java_Web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.example.Java_Web.dto.ProductDTO;
import com.example.Java_Web.domain.model.Product;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toDTO(Product product);
    Product toEntity(ProductDTO dto);
}
