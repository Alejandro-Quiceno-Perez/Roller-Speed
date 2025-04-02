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

@Entity
@Table(name = "gestionClases")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GestionClases {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la clase no puede estar vacío")
    private String name;
    
    @NotBlank(message = "La descripcion de la clase no puede estar vacío")
    private String description;

    @NotBlank(message = "La duracion de la clase no puede estar vacío")
    private String duration;

}
