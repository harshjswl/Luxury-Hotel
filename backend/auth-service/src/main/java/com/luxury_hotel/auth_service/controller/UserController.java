package com.luxury_hotel.auth_service.controller;


import com.luxury_hotel.auth_service.dtos.UserResponseDTO;
import com.luxury_hotel.auth_service.model.User;
import com.luxury_hotel.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO>getCurrentUser(Authentication authentication){
        String email=authentication.getName();
        User user =userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("User not found: "+email));
           return ResponseEntity.ok(UserResponseDTO.fromUser(user));
    }
}
