package com.luxury_hotel.auth_service.controller;

import com.luxury_hotel.auth_service.config.JwtTokenProvider;
import com.luxury_hotel.auth_service.dtos.*;
import com.luxury_hotel.auth_service.exception.TokenRefreshException;
import com.luxury_hotel.auth_service.model.RefreshToken;
import com.luxury_hotel.auth_service.model.User;
import com.luxury_hotel.auth_service.repository.UserRepository;
import com.luxury_hotel.auth_service.service.RefreshTokenService;
import com.luxury_hotel.auth_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDTO requestDTO) {
        String response = userService.registerUser(requestDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<TokenResponseDTO> verifyOtp(
            @RequestParam String email,
            @RequestParam String otp) {
        TokenResponseDTO response = userService.verifyOtp(email, otp);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/resend-otp")
    public ResponseEntity<String> resendOtp(@RequestParam String email) {
        String response = userService.resendOtp(email);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody ForgotPasswordRequestDTO requestDTO) {
        String response = userService.forgotPassword(requestDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordRequestDTO requestDTO) {
        String response = userService.restPassword(requestDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> authenticateUser(@Valid @RequestBody LoginRequestDto loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmailOrNumber(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // authentication.getName() returns the username set in CustomUserDetailsService (we use email)
        String principalEmail = authentication.getName();

        // find the User entity by email (principalEmail)
        User user = userRepository.findByEmail(principalEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + principalEmail));

        // create tokens
        String accessToken = jwtTokenProvider.generateAccessToken(user.getEmail());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return ResponseEntity.ok(TokenResponseDTO.builder()
                .message("Login successful.")
                .token(accessToken)
                .refreshToken(refreshToken.getToken())
                .user(UserResponseDTO.fromUser(user))
                .build());
    }
    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponseDTO> refreshToken(@Valid @RequestBody RefreshTokenRequestDTO request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jwtTokenProvider.generateAccessToken(user.getEmail());
                    return ResponseEntity.ok(RefreshTokenResponseDTO.builder()
                            .accessToken(accessToken)
                            .refreshToken(requestRefreshToken)
                            .build());
                })
                .orElseThrow(() -> new TokenRefreshException("Invalid refresh token"));
    }
}
