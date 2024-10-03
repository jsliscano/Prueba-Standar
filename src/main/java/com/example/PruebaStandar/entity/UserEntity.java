package com.example.PruebaStandar.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoIdentificacion;

    private Integer edad;

    @Column (name = "numero_identificacion", unique = true, nullable = false)
    @NotBlank(message = "El número de identificación no puede estar vacío")
    private String numeroIdentificacion;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private LocalDate fechaIngreso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo_id", nullable = false)
    private RoleEntity cargo;
}




