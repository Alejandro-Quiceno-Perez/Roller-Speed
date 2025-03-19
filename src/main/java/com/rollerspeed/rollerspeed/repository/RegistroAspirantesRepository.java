package com.rollerspeed.rollerspeed.repository;
import org.springframework.stereotype.Repository;

import com.rollerspeed.rollerspeed.entity.RegistroAspirantes;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RegistroAspirantesRepository extends JpaRepository<RegistroAspirantes, Long> {
       
}
