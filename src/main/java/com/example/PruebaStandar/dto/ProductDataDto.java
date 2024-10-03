package com.example.PruebaStandar.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDataDto {

    private String nombre;

    private int cantidad;

    private LocalDate fechaIngreso;

}
