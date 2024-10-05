package com.example.PruebaStandar.service;
import com.example.PruebaStandar.dto.UserRequestDto;
import com.example.PruebaStandar.dto.UserResponseDto;
import com.example.PruebaStandar.excepciones.ProductException;

public interface UserService {
    UserResponseDto createUser (UserRequestDto userRequestDto) throws ProductException;
}
