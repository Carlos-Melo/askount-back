package com.askount.config.validacao;

public class ErroFormularioDto {
	
	private String campo;
	private String messagemErro;
	
	public ErroFormularioDto(String campo, String messagemErro) {
		this.campo = campo;
		this.messagemErro = messagemErro;
	}

	public String getCampo() {
		return campo;
	}

	public String getMessagemErro() {
		return messagemErro;
	}
	
	
	
	
}
