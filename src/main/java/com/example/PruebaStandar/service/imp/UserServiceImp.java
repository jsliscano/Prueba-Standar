package com.example.PruebaStandar.service.imp;

import com.example.PruebaStandar.dto.UserRequestDto;
import com.example.PruebaStandar.dto.UserResponseDto;
import com.example.PruebaStandar.entity.RoleEntity;
import com.example.PruebaStandar.entity.UserEntity;
import com.example.PruebaStandar.excepciones.ProductException;
import com.example.PruebaStandar.repository.RolRepository;
import com.example.PruebaStandar.repository.UserRespository;
import com.example.PruebaStandar.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImp implements UserService {

    private final UserRespository userRespository;
    private final RolRepository rolRepository;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        try {
            RoleEntity roleEntity = rolRepository.findById(userRequestDto.getCargoId())
                    .orElseThrow(() -> new ProductException("Cargo no encontrado con id: " + userRequestDto.getCargoId()));

            UserEntity userEntity = UserEntity.builder()
                    .tipoIdentificacion(userRequestDto.getTipoIdentificacion())
                    .edad(userRequestDto.getEdad())
                    .numeroIdentificacion(userRequestDto.getNumeroIdentificacion())
                    .nombre(userRequestDto.getNombre())
                    .fechaIngreso(userRequestDto.getFechaIngreso())
                    .cargo(roleEntity)
                    .build();

            UserEntity savedUser = userRespository.save(userEntity);

            UserRequestDto userResponseDto = UserRequestDto.builder()
                    .tipoIdentificacion(savedUser.getTipoIdentificacion())
                    .edad(savedUser.getEdad())
                    .numeroIdentificacion(savedUser.getNumeroIdentificacion())
                    .nombre(savedUser.getNombre())
                    .fechaIngreso(savedUser.getFechaIngreso())
                    .cargoId(roleEntity.getId())
                    .nombreCargo(roleEntity.getNombre())
                    .build();

            return UserResponseDto.builder()
                    .code("200")
                    .message("Usuario creado exitosamente")
                    .data(userResponseDto)
                    .build();

        } catch (ProductException e) {
            return UserResponseDto.builder()
                    .code("400")
                    .message(e.getMessage())
                    .data(null)
                    .build();
        } catch (Exception e) {
            return UserResponseDto.builder()
                    .code("500")
                    .message("Error al crear el usuario: " + e.getMessage())
                    .data(null)
                    .build();
        }
    }
}
