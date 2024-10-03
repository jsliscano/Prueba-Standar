package com.example.PruebaStandar.controller;

import com.example.PruebaStandar.dto.RolRequestDto;
import com.example.PruebaStandar.entity.RoleEntity;
import com.example.PruebaStandar.excepciones.ProductException;
import com.example.PruebaStandar.service.RolService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/rol")
public class RolController {

    private final RolService rolService;

    @PostMapping("/create")
    public ResponseEntity<RoleEntity> createRole(@RequestBody RolRequestDto rolRequestDto) throws ProductException {
        RoleEntity createdRole = rolService.createRol(rolRequestDto); // Asegúrate de llamar al método correcto
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }
}


