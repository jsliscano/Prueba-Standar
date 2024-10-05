package com.example.PruebaStandar.mapper;

import com.example.PruebaStandar.dto.ProductDataDto;
import com.example.PruebaStandar.dto.ProductRequestDto;
import com.example.PruebaStandar.dto.ProductResponseDto;
import com.example.PruebaStandar.entity.ProductEntity;
import com.example.PruebaStandar.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    // Mapea de ProductRequestDto a ProductEntity
    public ProductEntity toEntity(ProductRequestDto productRequestDto, UserEntity userEntity) {
        return ProductEntity.builder()
                .nombre(productRequestDto.getNombre())
                .cantidad(productRequestDto.getCantidad())
                .fechaIngreso(productRequestDto.getFechaIngreso())
                .userEntity(userEntity)
                .build();
    }

    // Mapea de ProductEntity a ProductDataDto
    public ProductDataDto toDataDto(ProductEntity productEntity) {
        return ProductDataDto.builder()
                .id(productEntity.getId())
                .nombre(productEntity.getNombre())
                .cantidad(productEntity.getCantidad())
                .fechaIngreso(productEntity.getFechaIngreso())
                .nombreUsuario(productEntity.getUserEntity().getNombre())
                .build();
    }

    // Mapea de ProductEntity a ProductResponseDto
    public ProductResponseDto toResponseDto(ProductEntity productEntity) {
        ProductDataDto productDataDto = toDataDto(productEntity);
        return ProductResponseDto.builder()
                .code("200")
                .data(productDataDto)
                .build();
    }

    // Mapea una lista de ProductEntity a una lista de ProductDataDto
    public List<ProductDataDto> toDataDtoList(List<ProductEntity> productEntities) {
        return productEntities.stream()
                .map(this::toDataDto)
                .collect(Collectors.toList());
    }

    // Actualiza una entidad existente con los datos de ProductRequestDto
    public void updateProductFromDto(ProductRequestDto productRequestDto, ProductEntity existingProduct) {
        existingProduct.setNombre(productRequestDto.getNombre());
        existingProduct.setCantidad(productRequestDto.getCantidad());
        existingProduct.setFechaIngreso(productRequestDto.getFechaIngreso());
    }
}
