package com.rollerspeed.rollerspeed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rollerspeed.rollerspeed.entity.Usuario;
import com.rollerspeed.rollerspeed.repository.UsuarioRepository;

@Service
public class UsuarioService {
       @Autowired
       private UsuarioRepository objRegistroAspirantesRepository;

       public List<Usuario> findAll() {
              return this.objRegistroAspirantesRepository.findAll();
       }

       public Usuario save(Usuario objRegistroAspirantes) {

              return this.objRegistroAspirantesRepository.save(objRegistroAspirantes);
       }
       
       public Usuario guardar(Usuario objRegistroAspirantes) {

              return this.objRegistroAspirantesRepository.save(objRegistroAspirantes);
       }

       public void delete(Long id) {
              this.objRegistroAspirantesRepository.deleteById(id);
       }

       public Usuario update(Long id, Usuario objRegistroAspirantes) {
              // Buscamos el registro de aspirantes por id
              Usuario objRegistroAspirantesToUpdate = this.findById(id);

              // Validamos la existencia del registro de aspirantes
              if (objRegistroAspirantesToUpdate == null) {
                     return null;
              }

              // Actualizamos el registro de aspirantes con los nuevos datos
              objRegistroAspirantesToUpdate = objRegistroAspirantes;

              // Guardamos los cambios
              return this.objRegistroAspirantesRepository.save(objRegistroAspirantesToUpdate);
       }

       public Usuario findById(Long id) {
              return this.objRegistroAspirantesRepository.findById(id).orElse(null);
       }
}
