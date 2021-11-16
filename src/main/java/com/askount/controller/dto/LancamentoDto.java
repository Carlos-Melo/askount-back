package com.askount.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.askount.modelo.Conta;
import com.askount.modelo.Lancamento;

public class LancamentoDto {

	private Long id;
	private String descricao;
	private String servico;
	private String setor;
	private LocalDate dataCompetencia;
	private String fornecedor;
	private String valor;
	private Boolean recebimento;
	
	public LancamentoDto(Lancamento lancamento) {
		this.id = lancamento.getId();
		this.descricao = lancamento.getDescricao();
		this.servico = lancamento.getServico();
		this.setor = lancamento.getSetor();
		this.dataCompetencia = lancamento.getDataCompetencia();
		this.fornecedor = lancamento.getFornecedor();
		this.valor = lancamento.getValor();
		this.recebimento = lancamento.getRecebimento();
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public String getServico() {
		return servico;
	}

	public String getSetor() {
		return setor;
	}

	public LocalDate getDataCompetencia() {
		return dataCompetencia;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public String getValor() {
		return valor;
	}
	
	public Boolean getRecebimento() {
		return recebimento;
	}

	public static List<LancamentoDto> converter(List<Lancamento> lancamento) {
		return lancamento.stream().map(LancamentoDto::new).collect(Collectors.toList());
	}
	
}
