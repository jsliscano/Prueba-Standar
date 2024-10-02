package com.example.PruebaStandar.service;

import com.example.PruebaStandar.entity.ProductEntity;
import com.example.PruebaStandar.entity.UserEntity;
import com.example.PruebaStandar.excepciones.ProductException;

import java.util.List;

public interface ProductService {

    ProductEntity createProduct(ProductEntity product) throws ProductException;

    ProductEntity updateProduct(Long id, ProductEntity product, UserEntity user) throws ProductException;

    void deleteProduct(Long id, Long userId) throws ProductException;

    List<ProductEntity> getAllProducts();

    List<ProductEntity> searchProductsByName(String name) throws ProductException;

    List<ProductEntity> searchProductsByUser(Long userId) throws ProductException;
}



