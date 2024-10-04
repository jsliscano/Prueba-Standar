package com.example.PruebaStandar.controller;

import com.example.PruebaStandar.dto.UserRequestDto;
import com.example.PruebaStandar.dto.UserResponseDto;
import com.example.PruebaStandar.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponse = userService.createUser(userRequestDto);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }
}
