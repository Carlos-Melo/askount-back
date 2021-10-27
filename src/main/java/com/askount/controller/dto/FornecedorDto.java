package com.askount.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.askount.modelo.Fornecedor;

public class FornecedorDto {
	
	private Long id;
	private String descricao;
	private String fornecedor;
	
	public FornecedorDto(Fornecedor fornecedor) {
		this.id = fornecedor.getId();
		this.descricao = fornecedor.getDescricao();
		this.fornecedor = fornecedor.getFornecedor();
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getFornecedor() {
		return fornecedor;
	}
	
	public static List<FornecedorDto> converter(List<Fornecedor> fornecedor) {
		return fornecedor.stream().map(FornecedorDto::new).collect(Collectors.toList());
	}
}
