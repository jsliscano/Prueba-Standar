package com.example.PruebaStandar.controller;

import com.example.PruebaStandar.dto.RolDataDto;
import com.example.PruebaStandar.dto.RolRequestDto;
import com.example.PruebaStandar.dto.UserDataDto;
import com.example.PruebaStandar.dto.UserRequestDto;
import com.example.PruebaStandar.entity.RoleEntity;
import com.example.PruebaStandar.entity.UserEntity;
import com.example.PruebaStandar.excepciones.ProductException;
import com.example.PruebaStandar.repository.RoleRepository;
import com.example.PruebaStandar.service.RolService;
import com.example.PruebaStandar.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final RolService rolService;

    @PostMapping("/create")  // Asegúrate de que este es el endpoint correcto
    public ResponseEntity<RoleEntity> createRole(@RequestBody RolRequestDto rolRequestDto) throws ProductException {
        RoleEntity createdRole = rolService.createRol(rolRequestDto);  // Llama al método correcto
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

}
