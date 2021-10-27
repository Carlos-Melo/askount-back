package com.askount.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.askount.controller.dto.LancamentoDto;
import com.askount.controller.form.LancamentoForm;
import com.askount.modelo.Lancamento;
import com.askount.repository.ContaRepository;
import com.askount.repository.LancamentoRepository;

@RestController
@RequestMapping("/lancamento")
public class LancamentoController {
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@GetMapping
	public List<LancamentoDto> lista(Long idConta, Boolean recebimento){
		if(idConta == null && recebimento == null) {
			List<Lancamento> lancamento = lancamentoRepository.findAll();
			return LancamentoDto.converter(lancamento);
		}else if(idConta != null && recebimento == null){
			List<Lancamento> lancamento = lancamentoRepository.findByContaId(idConta);
			return LancamentoDto.converter(lancamento);
		}else {
			List<Lancamento> lancamento = lancamentoRepository.findByRecebimento(recebimento);
			return LancamentoDto.converter(lancamento);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<LancamentoDto> cadastrar(@RequestBody @Valid LancamentoForm lancamentoForm, UriComponentsBuilder uriBuilder) {
		Lancamento lancamento = lancamentoForm.converter(contaRepository);
		lancamentoRepository.save(lancamento);
		
		URI uri = uriBuilder.path("/lancamento/{id}").buildAndExpand(lancamento.getId()).toUri();
		return ResponseEntity.created(uri).body(new LancamentoDto(lancamento));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LancamentoDto> detalhar(@PathVariable Long id){
		Optional<Lancamento> lancamento = lancamentoRepository.findById(id);
		if(lancamento.isPresent()) {
			return ResponseEntity.ok(new LancamentoDto(lancamento.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<LancamentoDto> atualizar(@PathVariable Long id, @RequestBody @Valid LancamentoForm lancamentoForm) {
		Optional<Lancamento> optional = lancamentoRepository.findById(id);
		if(optional.isPresent()) {
			Lancamento lancamento = lancamentoForm.atualizar(id, lancamentoRepository);
			return ResponseEntity.ok(new LancamentoDto(lancamento));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Lancamento> optional = lancamentoRepository.findById(id);
		if(optional.isPresent()) {
			lancamentoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
