package com.askount.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.askount.modelo.Fornecedor;
import com.askount.modelo.Usuario;
import com.askount.repository.FornecedorRepository;
import com.askount.repository.UsuarioRepository;

public class FornecedorForm {
	
	@NotNull @NotEmpty
	private String descricao;
	@NotNull @NotEmpty
	private String fornecedor;
	private Long idUsuario;
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public Fornecedor converter(UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.getOne(idUsuario);
		return new Fornecedor(descricao, fornecedor, usuario);
	}
	
	public Fornecedor atualizar(Long id, FornecedorRepository fornecedorRepository) {
		
		Fornecedor fornecedor = fornecedorRepository.getOne(id);
		fornecedor.setDescricao(this.descricao);
		fornecedor.setFornecedor(this.fornecedor);
		
		return fornecedor;
	}
}
