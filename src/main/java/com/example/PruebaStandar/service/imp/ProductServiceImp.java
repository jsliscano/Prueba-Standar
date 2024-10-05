package com.example.PruebaStandar.service.imp;

import com.example.PruebaStandar.dto.ProductDataDto;
import com.example.PruebaStandar.dto.ProductRequestDto;
import com.example.PruebaStandar.dto.ProductResponseDto;
import com.example.PruebaStandar.entity.ProductEntity;
import com.example.PruebaStandar.entity.UserEntity;
import com.example.PruebaStandar.excepciones.ProductException;
import com.example.PruebaStandar.excepciones.ProductNotFoundException;
import com.example.PruebaStandar.mapper.ProductMapper;
import com.example.PruebaStandar.repository.ProductRepository;
import com.example.PruebaStandar.repository.UserRespository;
import com.example.PruebaStandar.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static com.example.PruebaStandar.constant.MessageGeneric.*;


@AllArgsConstructor
@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final UserRespository userRespository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) throws ProductException {

        if (productRepository.existsByNombre(productRequestDto.getNombre())) {
            throw new ProductException(PRODUCTO_YA_EXISTE );
        }

        if (productRequestDto.getCantidad() < 0) {
            throw new ProductException(CANTIDAD_NO_NEGATIVA);
        }

        if (productRequestDto.getFechaIngreso() == null || productRequestDto.getFechaIngreso().isAfter(LocalDate.now())) {
            throw new ProductException(ERROR_FECHA_INGRESO_INVALIDA);
        }

        Optional<UserEntity> userEntity = userRespository.findByNombre(productRequestDto.getUserNameRegister());

        ProductEntity productEntity = productMapper.toEntity(productRequestDto,userEntity.get());
        productEntity.setCreatedAt(ZonedDateTime.now());
        productEntity.setCreatedBy(productRequestDto.getUserNameRegister());

        productRepository.save(productEntity);

        ProductDataDto productDataDto = productMapper.toDataDto(productEntity);

        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .code("201")
                .message(PRODUCTO_CREADO)
                .data(productDataDto)
                .build();

        return productResponseDto;
    }

    @Override
    public ProductEntity updateProduct(Long id,ProductRequestDto productRequestDto) throws ProductException {
        ProductEntity existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductException(PRODUCTO_NO_ENCONTRADO_ID + id));

        if (productRequestDto.getCantidad() < 0) {
            throw new ProductException(CANTIDAD_NO_NEGATIVA);
        }

        if (productRequestDto.getFechaIngreso() == null || productRequestDto.getFechaIngreso().isAfter(LocalDate.now())) {
            throw new ProductException(ERROR_FECHA_INGRESO_INVALIDA);
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
                .orElseThrow(() -> new ProductException(PRODUCTO_NO_ENCONTRADO_ID));

        if (product.getCreatedBy() != null && product.getCreatedBy().equals(username)) {
            productRepository.delete(product);
        } else {
            throw new ProductException(PERMISO_ELIMINAR_PRODUCTO);
        }
    }

    @Override
    public ProductEntity findByNombre(String nombre) throws ProductException {
        Optional<ProductEntity> product = productRepository.findByNombreContainingIgnoreCase(nombre);
        if (product.isEmpty()) {
            throw new ProductNotFoundException(PRODUCTO_NO_ENCONTRADO_NOMBRE + nombre);
        }
        return product.get();
    }

    @Override
    public List<ProductEntity> findByFechaIngreso(LocalDate fechaIngreso) throws ProductNotFoundException {
        List<ProductEntity> products = productRepository.findByFechaIngreso(fechaIngreso);
        if (products.isEmpty()) {
            throw new ProductNotFoundException(PRODUCTO_NO_ENCONTRADO_FECHA + fechaIngreso);
        }
        return products;
    }
}
