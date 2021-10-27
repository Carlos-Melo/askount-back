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

import com.askount.controller.dto.ContaDto;
import com.askount.controller.form.AtualizaUsuarioForm;
import com.askount.controller.form.ContaForm;
import com.askount.modelo.Conta;
import com.askount.modelo.Usuario;
import com.askount.repository.ContaRepository;
import com.askount.repository.UsuarioRepository;

@RestController
@RequestMapping("/conta")
public class ContaController {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public List<ContaDto> lista(Long idUsuario) {
		if(idUsuario == null) {
			List<Conta> conta = contaRepository.findAll();
			return ContaDto.converter(conta);
		}else {
			List<Conta> conta = contaRepository.findByUsuarioId(idUsuario);
			return ContaDto.converter(conta);
		}
		
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ContaDto> cadastrar(@RequestBody @Valid ContaForm contaForm, UriComponentsBuilder uriBuilder){
		Conta conta = contaForm.converter(usuarioRepository);
		contaRepository.save(conta);
		
		URI uri = uriBuilder.path("/conta/{id}").buildAndExpand(conta.getId()).toUri();
		return ResponseEntity.created(uri).body(new ContaDto(conta));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ContaDto> detalhar(@PathVariable Long id){
		Optional<Conta> conta = contaRepository.findById(id);
		if(conta.isPresent()) {
			return ResponseEntity.ok(new ContaDto(conta.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ContaDto> atualizar(@PathVariable Long id, @RequestBody @Valid ContaForm contaForm) {
		Optional<Conta> optional = contaRepository.findById(id);
		if(optional.isPresent()) {
			Conta conta = contaForm.atualizar(id, contaRepository);
			return ResponseEntity.ok(new ContaDto(conta));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Conta> optional = contaRepository.findById(id);
		if(optional.isPresent()) {
			contaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
