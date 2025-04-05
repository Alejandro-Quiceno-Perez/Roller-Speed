package com.rollerspeed.rollerspeed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.rollerspeed.rollerspeed.repository.UsuarioRepository;

@Configuration
public class UserDetailsServiceConfig {

       @Bean
       public UserDetailsService userDetailsService(UsuarioRepository usuarioRepository) {
              return email -> usuarioRepository.findByEmail(email)
                            .map(user -> org.springframework.security.core.userdetails.User
                                          .withUsername(user.getEmail())
                                          .password(user.getPassword())
                                          .roles(user.getRol())
                                          .build())
                            .orElseThrow(() -> new RuntimeException("User not found"));
       }
}
