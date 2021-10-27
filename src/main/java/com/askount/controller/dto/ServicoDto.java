package com.askount.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.askount.modelo.Servico;

public class ServicoDto {
	
	private Long id;
	private String descricao;
	private String servico;
	
	public ServicoDto(Servico servico) {
		this.id = servico.getId();
		this.descricao = servico.getDescricao();
		this.servico = servico.getServico();
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
	
	public static List<ServicoDto> converter(List<Servico> servico) {
		return servico.stream().map(ServicoDto::new).collect(Collectors.toList());
	}
}
