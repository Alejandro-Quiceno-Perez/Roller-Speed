package com.rollerspeed.rollerspeed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rollerspeed.rollerspeed.entity.RegistroAsistencia;
import com.rollerspeed.rollerspeed.repository.RegistroAsistenciaRepository;

@Service
public class RegistroAsistenciaService {
    
    @Autowired
    private RegistroAsistenciaRepository registroAsistenciaRepository;

    public List<RegistroAsistencia> findAll() {
        return this.registroAsistenciaRepository.findAll();
    }

    public RegistroAsistencia save(RegistroAsistencia registroAsistencia) {
        return this.registroAsistenciaRepository.save(registroAsistencia);
    }

    public void delete(Long id) {
        this.registroAsistenciaRepository.deleteById(id);
    }

    public RegistroAsistencia update(Long id, RegistroAsistencia registroAsistencia) {
        // Buscamos el registro de asistencia por id
        RegistroAsistencia registroAsistenciaToUpdate = this.findById(id);

        // Validamos la existencia del registro de asistencia
        if (registroAsistenciaToUpdate == null) {
            return null;
        }

        // Actualizamos los valores del objeto existente
        registroAsistenciaToUpdate.setAlumno(registroAsistencia.getAlumno());
        registroAsistenciaToUpdate.setFecha(registroAsistencia.getFecha());
        registroAsistenciaToUpdate.setPresente(registroAsistencia.getPresente());

        // Guardamos los cambios
        return this.registroAsistenciaRepository.save(registroAsistenciaToUpdate);
    }

    public RegistroAsistencia findById(Long id) {
        return this.registroAsistenciaRepository.findById(id).orElse(null);
    }
}
