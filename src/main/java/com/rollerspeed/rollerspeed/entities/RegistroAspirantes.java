package com.rollerspeed.rollerspeed.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "registro_aspirantes")
public class RegistroAspirantes {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;
       private String nombre;




       public RegistroAspirantes(Long id, String nombre) {
              this.id = id;
              this.nombre = nombre;
       }

       
       public Long getId() {
              return id;
       }
       public void setId(Long id) {
              this.id = id;
       }
       public String getNombre() {
              return nombre;
       }
       public void setNombre(String nombre) {
              this.nombre = nombre;
       }

       
}
