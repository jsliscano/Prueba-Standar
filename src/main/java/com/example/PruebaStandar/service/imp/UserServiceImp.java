package com.example.PruebaStandar.service.imp;

import com.example.PruebaStandar.dto.UserRequestDto;
import com.example.PruebaStandar.entity.RoleEntity;
import com.example.PruebaStandar.entity.UserEntity;
import com.example.PruebaStandar.excepciones.ProductException;
import com.example.PruebaStandar.repository.RoleRepository;
import com.example.PruebaStandar.repository.UserRespository;
import com.example.PruebaStandar.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class UserServiceImp implements UserService {

    private final UserRespository userRespository;
    private final RoleRepository roleRepository;

    @Override
    public UserEntity createUser(UserRequestDto userRequestDto) throws ProductException {

        RoleEntity roleEntity = roleRepository.findById(userRequestDto.getCargoId())
                .orElseThrow(() -> new ProductException("Cargo no encontrado con id: " + userRequestDto.getCargoId()));

        UserEntity userEntity = UserEntity.builder()
                .tipoIdentificacion(userRequestDto.getTipoIdentificacion())
                .edad(userRequestDto.getEdad())
                .numeroIdentificacion(userRequestDto.getNumeroIdentificacion())
                .nombre(userRequestDto.getNombre())
                .fechaIngreso(userRequestDto.getFechaIngreso())
                .cargo(roleEntity)
                .build();

        return userRespository.save(userEntity);
    }


}