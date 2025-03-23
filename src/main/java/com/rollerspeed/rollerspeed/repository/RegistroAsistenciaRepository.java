package com.rollerspeed.rollerspeed.repository;

import org.springframework.stereotype.Repository;
import com.rollerspeed.rollerspeed.entity.RegistroAsistencia;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RegistroAsistenciaRepository extends JpaRepository<RegistroAsistencia, Long> {

}
