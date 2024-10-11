package com.example.PruebaStandar.service;

import com.example.PruebaStandar.dto.ProductDataDto;
import com.example.PruebaStandar.dto.ProductRequestDto;
import com.example.PruebaStandar.dto.ProductResponseDto;
import com.example.PruebaStandar.excepciones.ProductException;

import java.time.LocalDate;
import java.util.List;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto productRequestDto) throws ProductException;

    ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) throws ProductException;

    String deleteProduct(Long id, String username) throws ProductException;

    ProductDataDto findByNombre(String nombre);

    List<ProductDataDto> findByFechaIngreso(LocalDate fechaIngreso);

}




