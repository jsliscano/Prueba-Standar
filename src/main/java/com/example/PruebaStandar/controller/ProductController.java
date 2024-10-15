package com.example.PruebaStandar.controller;

import com.example.PruebaStandar.dto.ProductDataDto;
import com.example.PruebaStandar.dto.ProductRequestDto;
import com.example.PruebaStandar.dto.ProductResponseDto;
import com.example.PruebaStandar.mapper.ProductMapper;
import com.example.PruebaStandar.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Operation(summary = "Crear un producto", description = "Crea un nuevo producto y lo guarda en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de producto inválidos")
    })
    @PostMapping("/save")
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productRequestDto));
    }

    @Operation(summary = "Actualizar producto", description = "Actualiza los detalles de un producto existente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@Valid @PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.ok(productService.updateProduct(id, productRequestDto));
    }

    @Operation(summary = "Eliminar producto", description = "Elimina un producto por ID y registra el nombre de usuario que realizó la acción")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@Valid @PathVariable Long id, @RequestParam String username) {
        return ResponseEntity.ok(productService.deleteProduct(id, username));
    }

    @Operation(summary = "Buscar producto por nombre", description = "Obtiene un producto por su nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<ProductDataDto> getProductsByNombre(@Valid @PathVariable String nombre) {
        return ResponseEntity.ok(productService.findByNombre(nombre));
    }

    @Operation(summary = "Buscar productos por fecha", description = "Obtiene una lista de productos filtrados por la fecha de ingreso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos encontrados"),
            @ApiResponse(responseCode = "404", description = "No se encontraron productos para la fecha dada")
    })
    @GetMapping("/fecha/{fechaIngreso}")
    public ResponseEntity<List<ProductDataDto>> getProductsByFecha(@Valid @PathVariable LocalDate fechaIngreso) {
        return ResponseEntity.ok(productService.findByFechaIngreso(fechaIngreso));
    }
}
