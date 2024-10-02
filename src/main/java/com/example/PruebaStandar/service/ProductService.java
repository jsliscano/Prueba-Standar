package com.example.PruebaStandar.service;
import com.example.PruebaStandar.entity.ProductEntity;
import com.example.PruebaStandar.entity.UserEntity;
import com.example.PruebaStandar.excepciones.ProductException;

import java.util.List;

public interface ProductService {

    ProductEntity createProduct(ProductEntity product) throws ProductException;

    ProductEntity updateProduct(Long id, ProductEntity product, UserEntity user) throws ProductException;
}



