package com.example.PruebaStandar.dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ProductRequestDto {

    private String nombre;

    private int cantidad;

    private LocalDate fechaIngreso;

    private String userNameRegister;

}
