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

import com.askount.controller.dto.FornecedorDto;
import com.askount.controller.form.FornecedorForm;
import com.askount.modelo.Fornecedor;
import com.askount.repository.FornecedorRepository;
import com.askount.repository.UsuarioRepository;

@RestController
@RequestMapping("fornecedor")
public class FornecedorController {
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public List<FornecedorDto> lista(Long idUsuario){
		if(idUsuario == null) {
			List<Fornecedor> fornecedor = fornecedorRepository.findAll();
			return FornecedorDto.converter(fornecedor);
		}else {
			List<Fornecedor> fornecedor = fornecedorRepository.findByUsuarioId(idUsuario);
			return FornecedorDto.converter(fornecedor);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<FornecedorDto> cadastrar(@RequestBody @Valid FornecedorForm fornecedorForm, UriComponentsBuilder uriBuilder){
		Fornecedor fornecedor = fornecedorForm.converter(usuarioRepository);
		fornecedorRepository.save(fornecedor);
		
		URI uri = uriBuilder.path("/fornecedor/{id}").buildAndExpand(fornecedor.getId()).toUri();
		return ResponseEntity.created(uri).body(new FornecedorDto(fornecedor));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<FornecedorDto> detalhar(@PathVariable Long id){
		Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
		if(fornecedor.isPresent()) {
			return ResponseEntity.ok(new FornecedorDto(fornecedor.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<FornecedorDto> atualizar(@PathVariable Long id, @RequestBody @Valid FornecedorForm fornecedorForm) {
		Optional<Fornecedor> optional = fornecedorRepository.findById(id);
		if(optional.isPresent()) {
			Fornecedor fornecedor = fornecedorForm.atualizar(id, fornecedorRepository);
			return ResponseEntity.ok(new FornecedorDto(fornecedor));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Fornecedor> optional = fornecedorRepository.findById(id);
		if(optional.isPresent()) {
			fornecedorRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
