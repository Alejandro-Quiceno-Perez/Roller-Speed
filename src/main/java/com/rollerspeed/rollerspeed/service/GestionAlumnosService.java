package com.rollerspeed.rollerspeed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rollerspeed.rollerspeed.entity.GestionAlumnos;
import com.rollerspeed.rollerspeed.repository.GestionAlumnosRepository;

@Service
public class GestionAlumnosService {
       @Autowired
       private  GestionAlumnosRepository objGestionAlumnosRepository;

       public List< GestionAlumnos> findAll() {
              return this.objGestionAlumnosRepository.findAll();
       }

       public  GestionAlumnos save( GestionAlumnos objGestionAlumnos) {
              return this.objGestionAlumnosRepository.save(objGestionAlumnos);
       }

       public void delete(Long id) {
              this.objGestionAlumnosRepository.deleteById(id);
       }


       public  GestionAlumnos update(Long id,  GestionAlumnos objGestionAlumnos) {
              // Buscamos el registro de aspirantes por id
               GestionAlumnos objGestionAlumnosToUpdate = this.findById(id);

              //Validamos la existencia del registro de aspirantes
              if(objGestionAlumnosToUpdate == null) {
                     return null;
              }

              //Actualizamos el registro de aspirantes con los nuevos datos
              objGestionAlumnosToUpdate = objGestionAlumnos;

              //Guardamos los cambios
              return this.objGestionAlumnosRepository.save(objGestionAlumnosToUpdate);
       }

       public  GestionAlumnos findById(Long id) {
              return this.objGestionAlumnosRepository.findById(id).orElse(null);
       }
}
