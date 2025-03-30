package com.rollerspeed.rollerspeed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rollerspeed.rollerspeed.entity.RegistroInstructor;

@Repository

public interface RegistroInstructorRepository extends JpaRepository<RegistroInstructor, Long> {
 
}
