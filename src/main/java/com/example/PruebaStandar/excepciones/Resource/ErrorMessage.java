package com.example.PruebaStandar.excepciones.Resource;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Map;
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    private HttpStatus status;
    private String message;
    private Map<String, String> errors;
}
