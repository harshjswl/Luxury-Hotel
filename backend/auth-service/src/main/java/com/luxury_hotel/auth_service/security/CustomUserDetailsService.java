package com.luxury_hotel.auth_service.security;

import com.luxury_hotel.auth_service.model.User;
import com.luxury_hotel.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String emailOrNumber) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(emailOrNumber)
                .orElseGet(() -> userRepository.findByNumber(emailOrNumber)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with email/number: " + emailOrNumber)));

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toSet());

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .disabled(!user.isEnabled())
                .accountLocked(!user.isAccountNonLocked())
                .accountExpired(false)
                .credentialsExpired(false)
                .build();
    }
}
