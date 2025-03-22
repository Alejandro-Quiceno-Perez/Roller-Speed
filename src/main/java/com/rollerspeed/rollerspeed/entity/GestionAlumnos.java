package com.rollerspeed.rollerspeed.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "gestion_alumnos")
public class GestionAlumnos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGestionAlumnos;
    private String rol;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
    private String genero;
    private String telefono;
    private String metodoPago;

    // Getters y Setters
    public Long getIdGestionAlumnos() {
        return idGestionAlumnos;
    }

    public void setIdGestionAlumno(Long idGestionAlumnos) {
        this.idGestionAlumnos = idGestionAlumnos;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Override
    public String toString() {
        return "GestionAlumno {" +
                "idGestionAlumno=" + idGestionAlumnos +
                ", rol='" + rol + "'" +
                ", fechaNacimiento=" + fechaNacimiento +
                ", genero='" + genero + "'" +
                ", telefono='" + telefono + "'" +
                ", metodoPago='" + metodoPago + "'" +
                '}';
    }
}
