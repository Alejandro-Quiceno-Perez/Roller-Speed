package com.rollerspeed.rollerspeed.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "registro_asistencia")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroAsistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistroAsistencia;

    @NotBlank(message = "El nombre del alumno no puede estar vacío")
    private String alumno;

    @NotBlank(message = "El nombre de la clase no puede estar vacío")
    private String clase;

    @NotBlank(message = "La fecha no puede estar vacia")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    @NotBlank(message = "El estado de asistencia no puede estar vacío")
    private Boolean presente;
    
    // Getters y Setters
    
}
