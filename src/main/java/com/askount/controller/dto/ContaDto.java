package com.askount.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.askount.modelo.Conta;

public class ContaDto {
	
	private Long id;
	private String descricao;
	private String banco;
	private String tipoBanco;
	private String valorInicial;
	
	public ContaDto(Conta conta) {
		this.id = conta.getId();
		this.descricao = conta.getDescricao();
		this.banco = conta.getBanco();
		this.tipoBanco = conta.getTipoBanco();
		this.valorInicial = conta.getValorInicial();
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getBanco() {
		return banco;
	}

	public String getTipoBanco() {
		return tipoBanco;
	}

	public String getValorInicial() {
		return valorInicial;
	}
	
	public static List<ContaDto> converter(List<Conta> conta){
		return conta.stream().map(ContaDto::new).collect(Collectors.toList());
	}
}
