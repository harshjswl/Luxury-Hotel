package com.luxury_hotel.auth_service.service.impl;

import com.luxury_hotel.auth_service.config.JwtTokenProvider;
import com.luxury_hotel.auth_service.dtos.*;
import com.luxury_hotel.auth_service.exception.InvalidOtpException;
import com.luxury_hotel.auth_service.exception.OtpExpiredException;
import com.luxury_hotel.auth_service.exception.UserAlreadyExistsException;
import com.luxury_hotel.auth_service.exception.UserException;
import com.luxury_hotel.auth_service.model.RefreshToken;
import com.luxury_hotel.auth_service.model.Role;
import com.luxury_hotel.auth_service.model.User;
import com.luxury_hotel.auth_service.repository.UserRepository;
import com.luxury_hotel.auth_service.service.EmailService;
import com.luxury_hotel.auth_service.service.RefreshTokenService;
import com.luxury_hotel.auth_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private RefreshTokenService refreshTokenService;

    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    @Transactional
    public String registerUser(RegisterRequestDTO requestDTO) {
        if (userRepository.existsByEmail(requestDTO.getEmail())) {
            throw new UserAlreadyExistsException("Email is already in use.");
        }
        if (userRepository.existsByNumber(requestDTO.getNumber())) {
            throw new UserAlreadyExistsException("Number is already in use.");
        }

        String otp = generateOtp();
        User newUser = User.builder()
                .firstName(requestDTO.getFirstName())
                .lastName(requestDTO.getLastName())
                .email(requestDTO.getEmail())
                .number(requestDTO.getNumber())
                .password(passwordEncoder.encode(requestDTO.getPassword()))
                .otp(otp)
                .otpExpiry(LocalDateTime.now().plusMinutes(5))
                .enabled(false)
                .emailVerified(false)
                .accountNonLocked(true)
                .roles(requestDTO.getRoles() != null && !requestDTO.getRoles().isEmpty()
                        ? requestDTO.getRoles()
                        : Set.of(Role.ROLE_USER))
                .build();

        userRepository.save(newUser);
        emailService.sendOtpEmail(newUser.getEmail(), otp);

        return "Check email for OTP.";
    }

    @Override
    @Transactional
    public TokenResponseDTO verifyOtp(String email, String otp) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException("User not found with this email."));

        if (user.isEnabled()) {
            throw new UserException("Account is already verified.");
        }
        if (user.getOtp() == null || !user.getOtp().equals(otp)) {
            throw new InvalidOtpException("Invalid OTP.");
        }
        if (user.getOtpExpiry().isBefore(LocalDateTime.now())) {
            throw new OtpExpiredException("OTP has expired. Please request a new one.");
        }

        user.setEnabled(true);
        user.setEmailVerified(true);
        user.setOtp(null);
        user.setOtpExpiry(null);
        User savedUser = userRepository.save(user);

        String accessToken = jwtTokenProvider.generateAccessToken(savedUser.getEmail());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(savedUser);

        return TokenResponseDTO.builder()
                .message("Register successful.")
                .token(accessToken)
                .refreshToken(refreshToken.getToken())
                .user(UserResponseDTO.fromUser(savedUser))
                .build();
    }


    @Override
    @Transactional
    public String resendOtp(String email) {
        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new UserException("User not found with this email."));
        if(user.isEnabled()){
            throw new UserException("Account is already verified.");
        }
        String otp=generateOtp();
        user.setOtp(otp);
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(5));
        userRepository.save(user);

        emailService.sendOtpEmail(user.getEmail(), otp);
        return "A new OTP has been sent to your email.";
    }

    @Override
    @Transactional
    public String forgotPassword(ForgotPasswordRequestDTO requestDTO) {
        User user=userRepository.findByEmail(requestDTO.getEmailOrNumber())
                .or(()->userRepository.findByNumber(requestDTO.getEmailOrNumber()))
                .orElseThrow(()->new UserException("User not found with this email or number."));
        if(!user.isEnabled()){
            throw new UserException("Account is not enabled or verified.");
        }
        String otp=generateOtp();
        user.setOtp(otp);
        user.setOtpExpiry(LocalDateTime.now().plusMinutes(5));
        userRepository.save(user);
        emailService.sendPasswordResetOtpEmail(user.getEmail(), otp);
        return "OTP has been sent to your email for password reset.";
    }

    @Override
    @Transactional
    public String restPassword(ResetPasswordRequestDTO requestDTO) {
        User user = userRepository.findByEmail(requestDTO.getEmailOrNumber())
                .or(()->userRepository.findByNumber(requestDTO.getEmailOrNumber()))
                .orElseThrow(() -> new UserException("User not found with this email or number."));

        if (user.getOtp() == null || !user.getOtp().equals(requestDTO.getOtp())) {
            throw new InvalidOtpException("Invalid OTP.");
        }
        if (user.getOtpExpiry() == null || user.getOtpExpiry().isBefore(LocalDateTime.now())) {
            throw new OtpExpiredException("OTP has expired. Please request a new one.");
        }


        user.setPassword(passwordEncoder.encode(requestDTO.getNewPassword()));
        user.setOtp(null);
        user.setOtpExpiry(null);
        userRepository.save(user);

        return "Password has been reset successfully.";
    }


    private String generateOtp() {
        int otpValue = 100000 + this.secureRandom.nextInt(900000);
        return String.valueOf(otpValue);
    }
}
