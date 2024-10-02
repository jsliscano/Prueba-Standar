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
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductEntity createProduct(ProductEntity product) throws ProductException {
        if (productRepository.existsByNombre(product.getNombre())) {
            throw new ProductException("El nombre del producto ya existe.");
        }

        if (product.getCantidad() < 0) { // Cambiado a < 0 para permitir 0
            throw new ProductException("La cantidad debe ser un número entero no negativo.");
        }

        if (product.getFechaIngreso() == null || product.getFechaIngreso().isAfter(LocalDate.now())) {
            throw new ProductException("La fecha de ingreso debe ser hoy o en el pasado.");
        }

        return productRepository.save(product);
    }

    @Override
    public ProductEntity updateProduct(Long id, ProductEntity product, UserEntity user) throws ProductException {
        ProductEntity existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductException("Producto no encontrado."));

        if (product.getCantidad() < 0) { // Cambiado a < 0 para permitir 0
            throw new ProductException("La cantidad debe ser un número entero no negativo.");
        }

        if (product.getFechaIngreso() == null || product.getFechaIngreso().isAfter(LocalDate.now())) {
            throw new ProductException("La fecha de ingreso debe ser hoy o en el pasado.");
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
            throw new ProductException("No estás autorizado para eliminar este producto.");
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
            throw new ProductException("El nombre no puede estar en blanco.");
        }

        Optional<ProductEntity> optionalProduct = productRepository.findByNombre(name);

        if (optionalProduct.isPresent()) {
            return List.of(optionalProduct.get());
        } else {
            throw new ProductException("No se encontraron productos con el nombre: " + name);
        }
    }

    @Override
    public List<ProductEntity> searchProductsByUser(Long userId) throws ProductException {
        if (userId == null) {
            throw new ProductException("El ID de usuario no puede ser nulo.");
        }
        return productRepository.findByUsuarioRegistro_Id(userId);
    }
}
