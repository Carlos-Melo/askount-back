package com.askount.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.askount.modelo.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
	
	List<Lancamento> findByContaId(Long idConta);
	List<Lancamento> findByRecebimento(Boolean recebimento);
}
