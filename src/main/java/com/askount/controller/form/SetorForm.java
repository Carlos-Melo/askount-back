package com.askount.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.askount.modelo.Setor;
import com.askount.modelo.Usuario;
import com.askount.repository.SetorRepository;
import com.askount.repository.UsuarioRepository;

public class SetorForm {
	
	@NotNull @NotEmpty
	private String descricao;
	@NotNull @NotEmpty
	private String setor;
	private Long idUsuario;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public Setor converter(UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.getOne(idUsuario);
		return new Setor(descricao, setor, usuario);
	}
	
	public Setor atualizar(Long id, SetorRepository setorRepository) {
		
		Setor setor = setorRepository.getOne(id);
		setor.setDescricao(this.descricao);
		setor.setSetor(this.setor);
		
		return setor;
	}
}
