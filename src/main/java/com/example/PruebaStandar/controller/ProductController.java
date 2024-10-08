package com.example.PruebaStandar.controller;

import com.example.PruebaStandar.dto.ProductDataDto;
import com.example.PruebaStandar.dto.ProductRequestDto;
import com.example.PruebaStandar.dto.ProductResponseDto;
import com.example.PruebaStandar.entity.ProductEntity;
import com.example.PruebaStandar.excepciones.ProductException;
import com.example.PruebaStandar.mapper.ProductMapper;
import com.example.PruebaStandar.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static com.example.PruebaStandar.constant.MessageGeneric.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping("/save")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        ProductResponseDto productResponseService = productService.createProduct(productRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponseService);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id ,@RequestBody ProductRequestDto productRequestDto) throws ProductException {
        ProductEntity updatedProduct = productService.updateProduct(id,productRequestDto);
        ProductDataDto productDataDto = ProductDataDto
                .builder()
                .nombre(updatedProduct.getNombre())
                .cantidad(updatedProduct.getCantidad())
                .fechaIngreso(updatedProduct.getFechaIngreso())
                .build();

        ProductResponseDto productResponseDto =
                ProductResponseDto
                        .builder()
                        .code("200")
                        .data(productDataDto)
                        .build();
        return ResponseEntity.ok(productResponseDto);
    }


    @DeleteMapping("/delete/{id}/")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id, @RequestParam String username) {
        try {
            productService.deleteProduct(id, username);
            return ResponseEntity.ok(PRODUCTO_ELIMINADO);
        } catch (ProductException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(PRODUCTO_NO_ENCONTRADO_ID);
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<ProductDataDto> getProductsByNombre(@PathVariable String nombre) {
        try {
            ProductEntity product = productService.findByNombre(nombre);
            ProductResponseDto productResponseDto = productMapper.toResponseDto(product);
            return ResponseEntity.ok(productResponseDto.getData());
        } catch (ProductException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/fecha/{fechaIngreso}")
    public ResponseEntity<List<ProductDataDto>> getProductsByFecha(@PathVariable LocalDate fechaIngreso) {
        try {
            List<ProductEntity> products = productService.findByFechaIngreso(fechaIngreso);
            List<ProductDataDto> productDatumDtos = productMapper.toDataDtoList(products);
            return ResponseEntity.ok(productDatumDtos);
        } catch (ProductException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
