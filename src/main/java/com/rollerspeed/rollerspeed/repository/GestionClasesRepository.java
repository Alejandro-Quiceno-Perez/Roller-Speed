package com.rollerspeed.rollerspeed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.rollerspeed.rollerspeed.entity.GestionClases;

@Repository
public interface GestionClasesRepository extends JpaRepository<GestionClases, Long> {
    
}
