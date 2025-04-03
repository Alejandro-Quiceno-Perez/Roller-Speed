package com.rollerspeed.rollerspeed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.rollerspeed.rollerspeed.repository.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
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
       public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
              http
                            .authorizeHttpRequests(auth -> auth
                                          .requestMatchers("/viewUsuarios/").hasRole("ADMINISTRADOR")
                                          .requestMatchers("/viewRegistroInstructor/").hasRole("ADMINISTRADOR")
                                          .requestMatchers("/viewRegistroAsistencia/").hasRole("ADMINISTRADOR")
                                          .requestMatchers("/viewGestionAlumnos/").hasRole("ADMINISTRADOR")
                                          .anyRequest().authenticated())

                            .formLogin(from -> from
                                          .loginPage("/login")
                                          .defaultSuccessUrl("/index", true)
                                          .permitAll())

                            .logout(logout -> logout
                                          .logoutUrl("/logout") // URL para cerrar sesión
                                          .logoutSuccessHandler((request, response, authentication) -> {
                                                 response.sendRedirect("/login?logout"); // Redirige manualmente
                                          }) // Redirige a la página de login con
                                             // mensaje
                                          .invalidateHttpSession(true) // Invalida la sesión
                                          .clearAuthentication(true) // Limpia la autenticación
                                          .deleteCookies("JSESSIONID") // Borra la cookie de sesión
                                          .permitAll())
                            .sessionManagement(session -> session
                                          .sessionFixation().newSession() // Crea una nueva sesión al autenticar
                                          .maximumSessions(1).expiredUrl("/login?expired") // Expira la sesión si el
                                                                                           // usuario intenta iniciar
                                                                                           // sesión en otro dispositivo
                            );

              return http.build();
       }
}
