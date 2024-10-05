package com.example.PruebaStandar.service;

import com.example.PruebaStandar.dto.ProductRequestDto;
import com.example.PruebaStandar.dto.ProductResponseDto;
import com.example.PruebaStandar.entity.ProductEntity;
import com.example.PruebaStandar.excepciones.ProductException;

import java.time.LocalDate;
import java.util.List;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto productRequestDto) throws ProductException;

    ProductEntity updateProduct(Long id, ProductRequestDto productRequestDto) throws ProductException;

    void deleteProduct(Long id, String username) throws ProductException;

    ProductEntity findByNombre(String nombre);

    List<ProductEntity> findByFechaIngreso(LocalDate fechaIngreso);

}




