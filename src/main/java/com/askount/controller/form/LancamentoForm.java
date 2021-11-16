package com.askount.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.askount.modelo.Conta;
import com.askount.modelo.Lancamento;
import com.askount.repository.ContaRepository;
import com.askount.repository.LancamentoRepository;

public class LancamentoForm {

	@NotNull @NotEmpty
	private String descricao;
	private String servico;
	private String setor;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCompetencia;
	private String fornecedor;
	private String valor;
	private Boolean recebimento;
	private Long idConta;
	
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
	public Long getIdConta() {
		return idConta;
	}
	public void setIdConta(Long idConta) {
		this.idConta = idConta;
	}
	
	public Lancamento converter(ContaRepository contaRepository) {
		Conta conta = contaRepository.getOne(idConta);
		return new Lancamento(descricao, servico, setor, dataCompetencia, fornecedor, valor, recebimento, conta);
	}
	
	public Lancamento atualizar(Long id, LancamentoRepository lancamentoRepository) {
		Lancamento lancamento = lancamentoRepository.getOne(id);
		lancamento.setDescricao(this.descricao);
		lancamento.setServico(this.servico);
		lancamento.setDataCompetencia(this.dataCompetencia);
		lancamento.setFornecedor(this.fornecedor);
		lancamento.setRecebimento(this.recebimento);
		lancamento.setValor(this.valor);
		
		
		return lancamento;
	}
	
}
