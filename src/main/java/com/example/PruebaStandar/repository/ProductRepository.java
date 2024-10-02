package com.example.PruebaStandar.repository;

import com.example.PruebaStandar.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByNombre(String nombre);
    boolean existsByName(String nombre);

    List<ProductEntity> findByUserId(Long userId);

    List<ProductEntity> findByName(String name);
}
