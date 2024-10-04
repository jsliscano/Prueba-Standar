package com.example.PruebaStandar.repository;

import com.example.PruebaStandar.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<RoleEntity,Long> {
    boolean existsByNombre(String nombre);
}




