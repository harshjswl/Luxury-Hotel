package com.luxury_hotel.auth_service.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDTO {
    private String message;
    private String token; // access token
    private String refreshToken;
    private UserResponseDTO user;
}
