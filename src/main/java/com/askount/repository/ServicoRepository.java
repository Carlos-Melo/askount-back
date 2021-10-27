package com.askount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.askount.modelo.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
	List<Servico> findByUsuarioId(Long idUsuario);
}
