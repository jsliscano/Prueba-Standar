package com.example.PruebaStandar.service.imp;

import com.example.PruebaStandar.entity.ProductEntity;
import com.example.PruebaStandar.entity.UserEntity;
import com.example.PruebaStandar.excepciones.ProductException;
import com.example.PruebaStandar.repository.ProductRepository;
import com.example.PruebaStandar.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


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

    @Override
    public void deleteProduct(Long id, Long userId) throws ProductException {
            ProductEntity existingProduct = productRepository.findById(id)
                    .orElseThrow(() -> new ProductException("Producto no encontrado."));

            if (!existingProduct.getUsuarioRegistro().getId().equals(userId)) {
                throw new ProductException("No tienes permiso para eliminar este producto.");
            }

            productRepository.delete(existingProduct);
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();

    }

    @Override
    public List<ProductEntity> searchProductsByName(String name) throws ProductException {
            if (name == null || name.isEmpty()) {
                throw new ProductException("El nombre no puede estar vacío.");
            }
            return productRepository.findByName(name);
        }

    @Override
    public List<ProductEntity> searchProductsByUser(Long userId) throws ProductException {
        if (userId == null) {
            throw new ProductException("El ID del usuario no puede ser nulo.");
        }
        return productRepository.findByUserId(userId);
    }
}








