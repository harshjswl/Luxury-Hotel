package com.luxury_hotel.auth_service.repository;

import com.luxury_hotel.auth_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository  extends JpaRepository<User, UUID> {
    Optional<User>findByEmail(String email);
    Optional<User>findByNumber(String number);
    boolean existsByEmail(String email);
    boolean existsByNumber(String number);
}
