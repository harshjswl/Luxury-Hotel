package com.luxury_hotel.auth_service.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "Email or Number is required")
    private String emailOrNumber;

    @NotBlank(message = "Password is required")
    private String password;
}
