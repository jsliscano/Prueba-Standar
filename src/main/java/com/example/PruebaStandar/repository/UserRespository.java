package com.example.PruebaStandar.repository;

import com.example.PruebaStandar.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRespository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByNumeroIdentificacion(String numeroIdentificacion);
}