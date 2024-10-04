package com.example.PruebaStandar.service.imp;

import com.example.PruebaStandar.dto.ProductRequestDto;
import com.example.PruebaStandar.dto.ProductResponseDto;
import com.example.PruebaStandar.entity.ProductEntity;
import com.example.PruebaStandar.entity.UserEntity;
import com.example.PruebaStandar.excepciones.ProductException;
import com.example.PruebaStandar.excepciones.ProductNotFoundException;
import com.example.PruebaStandar.repository.ProductRepository;
import com.example.PruebaStandar.repository.UserRespository;
import com.example.PruebaStandar.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final UserRespository userRespository;

    @Override
    public ProductEntity createProduct(ProductRequestDto productRequestDto) throws ProductException {

        if (productRepository.existsByNombre(productRequestDto.getNombre())) {
            throw new ProductException("El producto con el nombre '" + productRequestDto.getNombre() + "' ya existe. No se puede guardar.");
        }

        if (productRequestDto.getCantidad() < 0) {
            throw new ProductException("La cantidad debe ser un número entero no negativo.");
        }

        if (productRequestDto.getFechaIngreso() == null || productRequestDto.getFechaIngreso().isAfter(LocalDate.now())) {
            throw new ProductException("La fecha de ingreso debe ser hoy o en el pasado.");
        }

        Optional<UserEntity> userEntity = userRespository.findByNombre(productRequestDto.getUserNameRegister());

        ProductEntity productEntity = ProductEntity.builder()
                .nombre(productRequestDto.getNombre())
                .cantidad(productRequestDto.getCantidad())
                .fechaIngreso(productRequestDto.getFechaIngreso())
                .userEntity(userEntity.orElseThrow(() -> new ProductException("Usuario no encontrado.")))
                .createdAt(ZonedDateTime.now())
                .createdBy(productRequestDto.getUserNameRegister())
                .build();
        return productRepository.save(productEntity);
    }

    @Override
    public ProductEntity updateProduct(Long id,ProductRequestDto productRequestDto) throws ProductException {
        ProductEntity existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductException("Producto no encontrado con id " + id));

        if (productRequestDto.getCantidad() < 0) {
            throw new ProductException("La cantidad debe ser un número entero no negativo.");
        }

        if (productRequestDto.getFechaIngreso() == null || productRequestDto.getFechaIngreso().isAfter(LocalDate.now())) {
            throw new ProductException("La fecha de ingreso debe ser hoy o en el pasado.");
        }

        Optional<UserEntity> userEntity = userRespository.findByNombre(productRequestDto.getUserNameRegister());

        existingProduct.setNombre(productRequestDto.getNombre());
        existingProduct.setCantidad(productRequestDto.getCantidad());
        existingProduct.setFechaIngreso(productRequestDto.getFechaIngreso());
        existingProduct.setUpdatedAt(ZonedDateTime.now());
        existingProduct.setUpdatedBy(productRequestDto.getUserNameRegister());


        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long id, String username) throws ProductException {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new ProductException("Producto no encontrado"));

        if (product.getCreatedBy() != null && product.getCreatedBy().equals(username)) {
            productRepository.delete(product);
        } else {
            throw new ProductException("No tienes permiso para eliminar este producto.");
        }
    }

    @Override
    public ProductEntity findByNombre(String nombre) throws ProductException {
        Optional<ProductEntity> product = productRepository.findByNombreContainingIgnoreCase(nombre);
        if (product.isEmpty()) {
            throw new ProductNotFoundException("No se encontraron productos con el nombre: " + nombre);
        }
        return product.get();
    }

    @Override
    public List<ProductEntity> findByFechaIngreso(LocalDate fechaIngreso) throws ProductNotFoundException {
        List<ProductEntity> products = productRepository.findByFechaIngreso(fechaIngreso);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("No se encontraron productos con la fecha de ingreso: " + fechaIngreso);
        }
        return products;
    }
}
