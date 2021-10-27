package com.askount.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Lancamento {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idLancamento")
	private Long id;
	private String descricao;
	private String servico;
	private String setor;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCompetencia;
	private String fornecedor;
	private String valor;
	private Boolean recebimento;
	@ManyToOne
    @JoinColumn(name = "idConta")
	private Conta conta;

	public Lancamento() {
	}
	
	public Lancamento(String descricao, String servico, String setor, LocalDate dataCompetencia, String fornecedor, String valor, Boolean recebimento, Conta conta) {
		this.descricao = descricao;
		this.servico = servico;
		this.setor = setor;
		this.dataCompetencia = dataCompetencia;
		this.fornecedor = fornecedor;
		this.valor = valor;
		this.recebimento = recebimento;
		this.conta = conta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public LocalDate getDataCompetencia() {
		return dataCompetencia;
	}

	public void setDataCompetencia(LocalDate dataCompetencia) {
		this.dataCompetencia = dataCompetencia;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Boolean getRecebimento() {
		return recebimento;
	}

	public void setRecebimento(Boolean recebimento) {
		this.recebimento = recebimento;
	}
	
}
