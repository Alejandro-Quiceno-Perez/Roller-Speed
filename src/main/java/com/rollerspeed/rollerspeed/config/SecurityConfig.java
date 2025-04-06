package com.rollerspeed.rollerspeed.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rollerspeed.rollerspeed.entity.Usuario;
import com.rollerspeed.rollerspeed.helpers.JwtAuthenticationFilter;
import com.rollerspeed.rollerspeed.helpers.JwtService;
import com.rollerspeed.rollerspeed.repository.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

       final String[] ADMIN_ENDPOINTS = {
                     "/viewUsuarios/**",
                     "/viewGestionClases/**",
                     "/viewGestionAlumnos/**",
                     "/viewRegistroAsistencia/**",
                     "/viewRegistroInstructor/**"
       };

       @Bean
       public BCryptPasswordEncoder passwordEncoder() {
              return new BCryptPasswordEncoder();
       }

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

       @Bean
       public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                     BCryptPasswordEncoder passwordEncoder) {
              DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
              provider.setUserDetailsService(userDetailsService);
              provider.setPasswordEncoder(passwordEncoder);
              return provider;
       }

       @Bean
       public AuthenticationManager authenticationManager(AuthenticationProvider authenticationProvider) {
              return authenticationProvider::authenticate;
       }

       @Bean
       public JwtAuthenticationFilter jwtAuthenticationFilter(JwtService jwtService,
                     UserDetailsService userDetailsService,
                     AuthenticationManager authenticationManager) {
              return new JwtAuthenticationFilter(jwtService, userDetailsService, authenticationManager);
       }

       @Bean
       public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                        JwtAuthenticationFilter jwtAuthenticationFilter,
                                                        AuthenticationProvider authenticationProvider) throws Exception {
              return http
                     .csrf(csrf -> csrf.disable())
                     .authorizeHttpRequests(auth -> auth
                            .requestMatchers(ADMIN_ENDPOINTS).hasRole("ADMINISTRADOR")
                            .requestMatchers("/viewLogin/**", "/index").permitAll()
                            .anyRequest().authenticated())
                     .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                     .authenticationProvider(authenticationProvider)
                     .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                     .build();
       }
}