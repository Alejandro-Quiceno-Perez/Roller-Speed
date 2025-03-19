package com.rollerspeed.rollerspeed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rollerspeed.rollerspeed.entity.RegistroAspirantes;
import com.rollerspeed.rollerspeed.repository.RegistroAspirantesRepository;

@Service
public class RegistroAspirantesService {
       @Autowired
       private RegistroAspirantesRepository objRegistroAspirantesRepository;

       public RegistroAspirantes save(RegistroAspirantes objRegistroAspirantes) {
              return this.objRegistroAspirantesRepository.save(objRegistroAspirantes);
       }
}
