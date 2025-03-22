package com.rollerspeed.rollerspeed.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "registro_asistencia")
public class RegistroAsistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistroAsistencia;
    private String alumno;
    private String clase;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;
    private Boolean presente;
    
    // Getters y Setters
    public Long getIdRegistroAsistencia() {
        return idRegistroAsistencia;
    }
    
    public void setIdRegistroAsistencia(Long idRegistroAsistencia) {
        this.idRegistroAsistencia = idRegistroAsistencia;
    }
    
    public String getAlumno() {
        return alumno;
    }
    
    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }
    
    public String getClase() {
        return clase;
    }
    
    public void setClase(String clase) {
        this.clase = clase;
    }
    
    public Date getFecha() {
        return fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public Boolean getPresente() {
        return presente;
    }
    
    public void setPresente(Boolean presente) {
        this.presente = presente;
    }
    
    @Override
    public String toString() {
        return "GestionAlumno {" +
               "id=" + idRegistroAsistencia +
               ", alumno=" + alumno +
               ", clase=" + clase +
               ", fecha=" + fecha +
               ", presente=" + presente +
               '}';
    }
}
