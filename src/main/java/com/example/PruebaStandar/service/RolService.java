package com.example.PruebaStandar.service;

import com.example.PruebaStandar.dto.RolRequestDto;
import com.example.PruebaStandar.entity.RoleEntity;
import com.example.PruebaStandar.excepciones.ProductException;

public interface RolService {
    RoleEntity createRol(RolRequestDto rolRequestDto) throws ProductException;
}
