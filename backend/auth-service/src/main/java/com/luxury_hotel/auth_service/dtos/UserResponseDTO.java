package com.luxury_hotel.auth_service.dtos;

import com.luxury_hotel.auth_service.model.Role;
import com.luxury_hotel.auth_service.model.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private String number;
    private String email;
    private Set<Role> roles;
    private boolean enabled;
    private boolean emailVerified;
    private boolean accountNonLocked;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static UserResponseDTO fromUser(User user){
        return UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .number(user.getNumber())
                .email(user.getEmail())
                .roles(user.getRoles() == null ? Collections.emptySet() : user.getRoles())
                .enabled(user.isEnabled())
                .emailVerified(user.isEmailVerified())
                .accountNonLocked(user.isAccountNonLocked())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
