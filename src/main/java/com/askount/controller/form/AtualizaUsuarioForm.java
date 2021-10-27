package com.askount.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.askount.modelo.Usuario;
import com.askount.repository.UsuarioRepository;

public class AtualizaUsuarioForm {

	@NotNull @NotEmpty
	private String nome;
	@NotNull @NotEmpty
	private String sobrenome;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate nascimento;
	@NotNull @NotEmpty
	private String email;
	@NotNull @NotEmpty
	private String senha;
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public LocalDate getNascimento() {
		return nascimento;
	}
	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Usuario atualizar(Long id, UsuarioRepository usuarioRepository) {
		
		Usuario usuario = usuarioRepository.getOne(id);
		usuario.setNome(this.nome);
		usuario.setSobrenome(this.sobrenome);
		usuario.setNascimento(this.nascimento);
		usuario.setEmail(this.email);
		usuario.setSenha(this.senha);
		
		return usuario;
	}
	
	
	
}
