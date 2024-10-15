package com.example.PruebaStandar.dto;

import lombok.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDataDto {

    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @Min(value = 1, message = "La cantidad debe ser un número entero positivo")
    private int cantidad;

    @PastOrPresent(message = "La fecha de ingreso debe ser menor o igual a la fecha actual")
    private LocalDate fechaIngreso;

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String nombreUsuario;
}
