package com.askount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.askount.modelo.Setor;

public interface SetorRepository extends JpaRepository<Setor, Long> {
	List<Setor> findByUsuarioId(Long idUsuario);
}
