package com.rollerspeed.rollerspeed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rollerspeed.rollerspeed.entity.GestionClases;
import com.rollerspeed.rollerspeed.repository.GestionClasesRepository;

@Service
public class GestionClasesService {
    @Autowired
    private GestionClasesRepository objGestionClasesRepository;

    public List <GestionClases> findAll() {
        return this.objGestionClasesRepository.findAll();
    }

    public GestionClases save (GestionClases objGestionClases) {
        return this.objGestionClasesRepository.save(objGestionClases);
    }

    public void delete(Long id) {
        this.objGestionClasesRepository.deleteById(id);
    }


    public GestionClases update(Long id, GestionClases objGestionClases) {
              // Buscamos la clase por id
              GestionClases objGestionClasesToUpdate = this.findById(id);

              //Validamos la existencia de la clase
              if(objGestionClasesToUpdate == null) {
                     return null;
              }

              //Actualizamos la clase con los nuevos datos
              objGestionClasesToUpdate = objGestionClases;

              //Guardamos los cambios
              return this.objGestionClasesRepository.save(objGestionClasesToUpdate);
       }

       public GestionClases findById(Long id) {
              return this.objGestionClasesRepository.findById(id).orElse(null);
       }

}
