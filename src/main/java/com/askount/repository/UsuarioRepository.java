package com.askount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.askount.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	List<Usuario> findById(int id);

}
