package com.example.PruebaStandar.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RolDataResponse {

    private String code;
    private String message;
    private UserRequestDto data;

}
