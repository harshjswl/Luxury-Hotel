package com.luxury_hotel.auth_service.service;

import com.luxury_hotel.auth_service.config.JwtTokenProvider;
import com.luxury_hotel.auth_service.exception.TokenRefreshException;
import com.luxury_hotel.auth_service.model.RefreshToken;
import com.luxury_hotel.auth_service.model.User;
import com.luxury_hotel.auth_service.repository.RefreshTokenRepository;
import com.luxury_hotel.auth_service.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {


    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Value("${jwt.refreshExpirationMs}")
    private long refreshTokenValidityMs;


    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }


    @Transactional
    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(LocalDateTime.now().plusSeconds(refreshTokenValidityMs / 1000))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }


    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException("Refresh token was expired. Please make a new signin request");
        }
        return token;
    }


    @Transactional
    public int deleteByUserId(UUID userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) return 0;
        return refreshTokenRepository.deleteByUser(user);
    }
}