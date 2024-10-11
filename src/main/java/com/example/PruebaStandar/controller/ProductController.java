package com.example.PruebaStandar.controller;


import com.example.PruebaStandar.dto.ProductDataDto;
import com.example.PruebaStandar.dto.ProductRequestDto;
import com.example.PruebaStandar.dto.ProductResponseDto;
import com.example.PruebaStandar.mapper.ProductMapper;
import com.example.PruebaStandar.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {


    private final ProductService productService;
    private final ProductMapper productMapper;

    @PostMapping("/save")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productRequestDto));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id ,@RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(productService.updateProduct(id,productRequestDto));
    }

    @DeleteMapping("/delete/{id}/")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id, @RequestParam String username) {
        return ResponseEntity.ok(productService.deleteProduct(id, username));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<ProductDataDto> getProductsByNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(productService.findByNombre(nombre));
    }

    @GetMapping("/fecha/{fechaIngreso}")
    public ResponseEntity<List<ProductDataDto>> getProductsByFecha(@PathVariable LocalDate fechaIngreso) {
        return ResponseEntity.ok(productService.findByFechaIngreso(fechaIngreso));
    }
}