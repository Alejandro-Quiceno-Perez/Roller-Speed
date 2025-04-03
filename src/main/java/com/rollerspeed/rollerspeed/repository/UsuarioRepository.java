package com.rollerspeed.rollerspeed.repository;

import org.springframework.stereotype.Repository;

import com.rollerspeed.rollerspeed.entity.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

       Optional<Usuario> findByEmail(String email); // Método para buscar un usuario por su correo electrónico
}
