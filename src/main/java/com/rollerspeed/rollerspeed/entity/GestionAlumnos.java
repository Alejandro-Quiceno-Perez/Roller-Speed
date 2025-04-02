package com.rollerspeed.rollerspeed.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "gestion_alumnos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GestionAlumnos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGestionAlumnos;

    @NotBlank(message = "El Rol no puede estar vacío")
    private String rol;

    @NotBlank(message = "La fecha de nacimiento no puede estar vacío")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

    @NotBlank(message = "El genero no puede estar vacío")
    private String genero;

    @NotBlank(message = "El telefono no puede estar vacío")
    private String telefono;

    @NotBlank(message = "El metodo de pago no puede estar vacío")
    private String metodoPago;

    

}
