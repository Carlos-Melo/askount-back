package com.askount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.askount.modelo.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

	List<Conta> findByUsuarioId(Long idUsuario);

}
