package com.rollerspeed.rollerspeed.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.rollerspeed.rollerspeed.entity.RegistroInstructor;
import com.rollerspeed.rollerspeed.repository.RegistroInstructorRepository;
import java.util.List;

@Service

public class RegistroInstructorService {

    @Autowired
    private RegistroInstructorRepository objRegistroInstructorRepository;

    public RegistroInstructor findById(Long id) {
        return this.objRegistroInstructorRepository.findById(id).orElse(null);
    }

    public List<RegistroInstructor> findAll() {
        return this.objRegistroInstructorRepository.findAll();
    }

    public RegistroInstructor save(RegistroInstructor objRegistroInstructor) {
        return this.objRegistroInstructorRepository.save(objRegistroInstructor);
    }

    public void delete(Long id) {
        this.objRegistroInstructorRepository.deleteById(id);
    }

    public RegistroInstructor update(Long id, RegistroInstructor objRegistroInstructor) {
        RegistroInstructor objRegistroInstructorUpdate = this.findById(id);

        if (objRegistroInstructorUpdate == null) {
            return null;
        }

        objRegistroInstructorUpdate = objRegistroInstructor;
        return this.objRegistroInstructorRepository.save(objRegistroInstructorUpdate);

    }
}