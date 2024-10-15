package com.example.PruebaStandar.excepciones;

import com.example.PruebaStandar.dto.ProductResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ProductResponseDto> handleProductException(ProductException e) {
        ProductResponseDto response = ProductResponseDto.builder()
                .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProductResponseDto> handleGenericException(Exception e) {
        ProductResponseDto response = ProductResponseDto.builder()
                .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .message("Ocurrió un error interno en el servidor")
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
