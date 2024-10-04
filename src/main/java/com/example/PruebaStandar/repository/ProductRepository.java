package com.example.PruebaStandar.repository;

import com.example.PruebaStandar.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    boolean existsByNombre(String nombre);

    List<ProductEntity> findByFechaIngreso(LocalDate fecha);

    Optional<ProductEntity> findByNombreContainingIgnoreCase(String nombre);

}


