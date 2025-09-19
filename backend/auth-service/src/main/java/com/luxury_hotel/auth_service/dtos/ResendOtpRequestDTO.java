package com.luxury_hotel.auth_service.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResendOtpRequestDTO {
    @NotBlank
    @Email
    private String email;
}
