package com.rollerspeed.rollerspeed.repository;

import org.springframework.stereotype.Repository;
import com.rollerspeed.rollerspeed.entity.GestionAlumnos;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface GestionAlumnosRepository extends JpaRepository<GestionAlumnos, Long> {

}
