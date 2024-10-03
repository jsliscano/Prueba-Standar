package com.example.PruebaStandar.dto;
import lombok.*;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDataDto {

    private String tipoIdentificacion;

    private Integer edad;

    private String numeroIdentificacion;

    private String nombre;

    private LocalDate fechaIngreso;

    private String cargo;
}
