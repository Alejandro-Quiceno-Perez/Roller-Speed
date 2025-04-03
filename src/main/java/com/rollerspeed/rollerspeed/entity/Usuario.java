package com.rollerspeed.rollerspeed.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "registro_usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor

// @Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @NotBlank(message = "El nombre no puede estar vacío")
       private String name;

       @NotBlank(message = "El apellido no puede estar vacío")
       private String lastName;

       @NotBlank(message = "El correo no puede estar vacío")
       private String email;

       @NotBlank(message = "La contraseña no puede estar vacío")
       private String password;

       @NotBlank(message = "El rol no puede estar vacío")
       private String rol;

       // Método para encriptar la contraseña antes de guardar en la BD
       public void setPassword(String password) {
              this.password = new BCryptPasswordEncoder().encode(password);
       }
}
