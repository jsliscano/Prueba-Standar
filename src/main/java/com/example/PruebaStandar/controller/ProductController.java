package com.example.PruebaStandar.controller;

import com.example.PruebaStandar.entity.ProductEntity;
import com.example.PruebaStandar.entity.UserEntity;
import com.example.PruebaStandar.excepciones.ProductException;
import com.example.PruebaStandar.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/products")

public class ProductController {

    private final ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity product) {
        try {
            ProductEntity createdProduct = productService.createProduct(product);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (ProductException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable Long id, @RequestBody ProductEntity product, @RequestParam Long userId) {
        try {
            UserEntity user = new UserEntity();
            user.setId(userId);

            ProductEntity updatedProduct = productService.updateProduct(id, product, user);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (ProductException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id, @RequestParam Long userId) {
        try {
            productService.deleteProduct(id, userId);
            return ResponseEntity.noContent().build(); // Es una forma más limpia de retornar no contenido
        } catch (ProductException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        List<ProductEntity> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<ProductEntity>> searchProductsByName(@RequestParam String name) {
        try {
            List<ProductEntity> products = productService.searchProductsByName(name);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (ProductException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/search/user")
    public ResponseEntity<List<ProductEntity>> searchProductsByUser(@RequestParam Long userId) {
        try {
            List<ProductEntity> products = productService.searchProductsByUser(userId);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (ProductException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
