package com.askount.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.askount.modelo.Setor;

public class SetorDto {
	private Long id;
	private String descricao;
	private String setor;
	
	public SetorDto(Setor setor) {
		this.id = setor.getId();
		this.descricao = setor.getDescricao();
		this.setor = setor.getSetor();
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getSetor() {
		return setor;
	}
	
	public static List<SetorDto> converter(List<Setor> setor) {
		return setor.stream().map(SetorDto::new).collect(Collectors.toList());
	}
}
