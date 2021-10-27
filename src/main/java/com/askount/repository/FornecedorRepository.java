package com.askount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.askount.modelo.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
	List<Fornecedor> findByUsuarioId(Long idUsuario);
}
