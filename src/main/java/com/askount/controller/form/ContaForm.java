package com.askount.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.askount.modelo.Conta;
import com.askount.modelo.Usuario;
import com.askount.repository.ContaRepository;
import com.askount.repository.UsuarioRepository;

public class ContaForm {
	
	@NotNull @NotEmpty
	private String descricao;
	@NotNull @NotEmpty
	private String banco;
	@NotNull @NotEmpty
	private String tipoBanco;
	private String valorInicial;
	private Long idUsuario;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getTipoBanco() {
		return tipoBanco;
	}
	public void setTipoBanco(String tipoBanco) {
		this.tipoBanco = tipoBanco;
	}
	public String getValorInicial() {
		return valorInicial;
	}
	public void setValorInicial(String valorInicial) {
		this.valorInicial = valorInicial;
	}
	
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public Conta converter(UsuarioRepository usuarioRepository) {
		Usuario usuario = usuarioRepository.getOne(idUsuario);
		return new Conta(descricao, banco, tipoBanco, valorInicial, usuario);
	}
	
	public Conta atualizar(Long id, ContaRepository contaRepository) {
		
		Conta conta = contaRepository.getOne(id);
		conta.setDescricao(this.descricao);
		conta.setBanco(this.banco);
		conta.setTipoBanco(this.tipoBanco);
		conta.setValorInicial(this.valorInicial);
		
		return conta;
	}
}
