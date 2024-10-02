package com.example.PruebaStandar.service.imp;

import com.example.PruebaStandar.entity.ProductEntity;
import com.example.PruebaStandar.entity.UserEntity;
import com.example.PruebaStandar.excepciones.ProductException;
import com.example.PruebaStandar.repository.ProductRepository;
import com.example.PruebaStandar.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@AllArgsConstructor
@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public ProductEntity createProduct(ProductEntity product) throws ProductException {

        if (productRepository.existsByName(product.getNombre())) {
            throw new ProductException("product name already exists.");
        }

        if (product.getCantidad() <= 0) {
            throw new ProductException("must be a positive integer.");
        }

        if (product.getFechaIngreso() == null || product.getFechaIngreso().isAfter(LocalDate.now())) {
            throw new ProductException("Entry date must be today or in the past.");
        }

        return productRepository.save(product);
    }

    @Override
    public ProductEntity updateProduct(Long id, ProductEntity product, UserEntity user) throws ProductException {
        ProductEntity existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductException("Product not found."));

        if (product.getCantidad() <= 0) {
            throw new ProductException("must be a positive integer.");
        }

        if (product.getFechaIngreso() == null || product.getFechaIngreso().isAfter(LocalDate.now())) {
            throw new ProductException("Entry date must be today or in the past.");
        }

        existingProduct.setNombre(product.getNombre());
        existingProduct.setCantidad(product.getCantidad());
        existingProduct.setFechaIngreso(product.getFechaIngreso());
        existingProduct.setUsuarioModificacion(user);
        existingProduct.setFechaModificacion(LocalDate.now());

        return productRepository.save(existingProduct);
    }



}

