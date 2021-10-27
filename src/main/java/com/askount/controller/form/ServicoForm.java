package com.askount.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.askount.modelo.Servico;
import com.askount.modelo.Usuario;
import com.askount.repository.ServicoRepository;
import com.askount.repository.UsuarioRepository;

public class ServicoForm {
	
	@NotNull @NotEmpty
	private String descricao;
	@NotNull @NotEmpty
	private String servico;
	private Long idUsuario;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public Servico converter(UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.getOne(idUsuario);
		return new Servico(descricao, servico, usuario);
	}
	
	public Servico atualizar(Long id, ServicoRepository servicoRepository) {
		
		Servico servico = servicoRepository.getOne(id);
		servico.setDescricao(this.descricao);
		servico.setServico(this.servico);
		
		return servico;
	}
}
