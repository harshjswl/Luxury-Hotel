package com.luxury_hotel.auth_service.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenResponseDTO {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";
}
