package com.example.PruebaStandar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "producto")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del producto es obligatorio.")
    @Column(unique = true, nullable = false)
    private String nombre;

    @Min(value = 0, message = "La cantidad debe ser un número entero no negativo.")
    private int cantidad;

    @PastOrPresent(message = "La fecha de ingreso debe ser menor o igual a la fecha actual.")
    private LocalDate fechaIngreso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserEntity userEntity;


    private ZonedDateTime createdAt;
    private String createdBy;


    private ZonedDateTime updatedAt;
    private String updatedBy;

}
