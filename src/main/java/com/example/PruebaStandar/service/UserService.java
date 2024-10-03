package com.example.PruebaStandar.service;
import com.example.PruebaStandar.dto.UserRequestDto;
import com.example.PruebaStandar.entity.UserEntity;
import com.example.PruebaStandar.excepciones.ProductException;

public interface UserService {

    UserEntity createUser(UserRequestDto userRequestDto) throws ProductException;
}
