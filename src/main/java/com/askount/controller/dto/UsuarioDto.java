package com.askount.controller.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.askount.modelo.Usuario;

public class UsuarioDto {

	private Long id;
	private String nome;
	private String sobrenome;
	private LocalDate nascimento;
	private String email;
	private String senha;
	private Boolean termos;
	
	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.sobrenome = usuario.getSobrenome();
		this.nascimento = usuario.getNascimento();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.termos = usuario.getTermos();
	}
	
	public Long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public LocalDate getNascimento() {
		return nascimento;
	}
	public String getEmail() {
		return email;
	}
	public String getSenha() {
		return senha;
	}
	public Boolean getTermos() {
		return termos;
	}

	public static List<UsuarioDto> converter(List<Usuario> usuarios) {
		return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
	}
	
	
	
}
