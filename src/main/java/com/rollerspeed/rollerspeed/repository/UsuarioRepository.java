package com.rollerspeed.rollerspeed.repository;

import org.springframework.stereotype.Repository;

import com.rollerspeed.rollerspeed.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

       
}
