package com.luxury_hotel.auth_service.service;

import com.luxury_hotel.auth_service.dtos.ForgotPasswordRequestDTO;
import com.luxury_hotel.auth_service.dtos.RegisterRequestDTO;
import com.luxury_hotel.auth_service.dtos.ResetPasswordRequestDTO;
import com.luxury_hotel.auth_service.dtos.TokenResponseDTO;

public interface UserService {
    String registerUser(RegisterRequestDTO requestDTO);
    TokenResponseDTO verifyOtp(String email, String otp);
    String resendOtp(String email);
    String forgotPassword(ForgotPasswordRequestDTO requestDTO);
    String restPassword(ResetPasswordRequestDTO requestDTO);
}
