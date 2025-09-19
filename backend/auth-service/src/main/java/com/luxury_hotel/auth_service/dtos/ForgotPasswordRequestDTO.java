package com.luxury_hotel.auth_service.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordRequestDTO {
    @NotBlank(message = "Email or phone number is required")
    private String emailOrNumber;
}
