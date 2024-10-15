package com.example.PruebaStandar.service.imp;

import com.example.PruebaStandar.entity.ProductEntity;
import com.example.PruebaStandar.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImpTest {

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createProduct() {
        ProductEntity productEntity = new ProductEntity();
        List<ProductEntity> productEntityList = new ArrayList<>();

        boolean productExist = true;
        Mockito.when(productRepository.existsByNombre("")).thenReturn(productExist);
        Mockito.when(productRepository.findByFechaIngreso(LocalDate.now())).thenReturn(productEntityList);
    }
}