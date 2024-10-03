package com.example.PruebaStandar.controller;

import com.example.PruebaStandar.dto.ProductDataDto;
import com.example.PruebaStandar.dto.ProductRequestDto;
import com.example.PruebaStandar.dto.ProductResponseDto;
import com.example.PruebaStandar.entity.ProductEntity;
import com.example.PruebaStandar.excepciones.ProductException;
import com.example.PruebaStandar.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        try {
            ProductEntity createdProduct = productService.createProduct(productRequestDto);

            ProductDataDto productDtoData  = ProductDataDto
                    .builder()
                    .nombre(createdProduct.getNombre())
                    .cantidad(createdProduct.getCantidad())
                    .fechaIngreso(createdProduct.getFechaIngreso())
                    .build();

            ProductResponseDto productResponseDto =
                    ProductResponseDto
                            .builder()
                            .code("200")
                            .data(productDtoData)
                            .build();

            return new ResponseEntity<>(productResponseDto, HttpStatus.CREATED);
        } catch (ProductException e) {
            return ResponseEntity.badRequest().body(null); // Puedes cambiar null por un mensaje de error
        }
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id, @RequestParam String username) throws ProductException {
        productService.deleteProduct(id, username);
        return ResponseEntity.ok("Producto eliminado con éxito.");
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<ProductResponseDto> getProductsByNombre(@PathVariable String nombre) {

        ProductEntity products = productService.findByNombre(nombre);
        ProductDataDto productDataDto = ProductDataDto
                .builder()
                .nombre(products.getNombre())
                .cantidad(products.getCantidad())
                .fechaIngreso(products.getFechaIngreso())
                .build();

        ProductResponseDto productResponseDto =
                ProductResponseDto
                        .builder()
                        .code("200")
                        .data(productDataDto)
                        .build();
        return ResponseEntity.ok(productResponseDto);
    }

    @GetMapping("/fecha/{fechaIngreso}")
    public ResponseEntity<List<ProductDataDto>> getProductsByFecha(@PathVariable LocalDate fechaIngreso) {
        List<ProductEntity> products = productService.findByFechaIngreso(fechaIngreso);

        List<ProductDataDto> productDatumDtos = products.stream()
                .map(product -> ProductDataDto.builder()
                        .nombre(product.getNombre())
                        .cantidad(product.getCantidad())
                        .fechaIngreso(product.getFechaIngreso())
                        .build())
                .collect(Collectors.toList());


        return ResponseEntity.ok(productDatumDtos);
    }
}







