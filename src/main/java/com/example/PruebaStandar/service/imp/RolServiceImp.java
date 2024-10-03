package com.example.PruebaStandar.service.imp;

import com.example.PruebaStandar.dto.RolRequestDto;
import com.example.PruebaStandar.entity.RoleEntity;
import com.example.PruebaStandar.excepciones.ProductException;
import com.example.PruebaStandar.repository.RoleRepository;
import com.example.PruebaStandar.service.RolService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RolServiceImp implements RolService {

    private final RoleRepository roleRepository;


    @Override
    public RoleEntity createRol(RolRequestDto rolRequestDto) throws ProductException {
        // Verificar si el rol ya existe
        if (roleRepository.existsByNombre(rolRequestDto.getNombre())) {
            throw new ProductException("El rol ya existe con nombre: " + rolRequestDto.getNombre());
        }
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setNombre(rolRequestDto.getNombre());

        return roleRepository.save(roleEntity);
    }

}

