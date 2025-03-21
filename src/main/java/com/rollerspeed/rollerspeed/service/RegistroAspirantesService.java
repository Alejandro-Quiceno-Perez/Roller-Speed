package com.rollerspeed.rollerspeed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rollerspeed.rollerspeed.entity.RegistroAspirantes;
import com.rollerspeed.rollerspeed.repository.RegistroAspirantesRepository;

@Service
public class RegistroAspirantesService {
       @Autowired
       private RegistroAspirantesRepository objRegistroAspirantesRepository;

       public List<RegistroAspirantes> findAll() {
              return this.objRegistroAspirantesRepository.findAll();
       }

       public RegistroAspirantes save(RegistroAspirantes objRegistroAspirantes) {
              return this.objRegistroAspirantesRepository.save(objRegistroAspirantes);
       }

       public void delete(Long id) {
              this.objRegistroAspirantesRepository.deleteById(id);
       }


       public RegistroAspirantes update(Long id, RegistroAspirantes objRegistroAspirantes) {
              // Buscamos el registro de aspirantes por id
              RegistroAspirantes objRegistroAspirantesToUpdate = this.findById(id);

              //Validamos la existencia del registro de aspirantes
              if(objRegistroAspirantesToUpdate == null) {
                     return null;
              }

              //Actualizamos el registro de aspirantes con los nuevos datos
              objRegistroAspirantesToUpdate = objRegistroAspirantes;

              //Guardamos los cambios
              return this.objRegistroAspirantesRepository.save(objRegistroAspirantesToUpdate);
       }

       public RegistroAspirantes findById(Long id) {
              return this.objRegistroAspirantesRepository.findById(id).orElse(null);
       }
}
