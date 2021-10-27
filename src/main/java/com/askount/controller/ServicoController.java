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

import com.askount.controller.dto.ServicoDto;
import com.askount.controller.form.ServicoForm;
import com.askount.modelo.Servico;
import com.askount.repository.ServicoRepository;
import com.askount.repository.UsuarioRepository;

@RestController
@RequestMapping("servico")
public class ServicoController {
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public List<ServicoDto> lista(Long idUsuario){
		if(idUsuario == null) {
			List<Servico> servico = servicoRepository.findAll();
			return ServicoDto.converter(servico);
		}else {
			List<Servico> servico = servicoRepository.findByUsuarioId(idUsuario);
			return ServicoDto.converter(servico);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ServicoDto> cadastrar(@RequestBody @Valid ServicoForm servicoForm, UriComponentsBuilder uriBuilder){
		Servico servico = servicoForm.converter(usuarioRepository);
		servicoRepository.save(servico);
		
		URI uri = uriBuilder.path("/servico/{id}").buildAndExpand(servico.getId()).toUri();
		return ResponseEntity.created(uri).body(new ServicoDto(servico));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ServicoDto> detalhar(@PathVariable Long id){
		Optional<Servico> servico = servicoRepository.findById(id);
		if(servico.isPresent()) {
			return ResponseEntity.ok(new ServicoDto(servico.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ServicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid ServicoForm servicoForm) {
		Optional<Servico> optional = servicoRepository.findById(id);
		if(optional.isPresent()) {
			Servico servico = servicoForm.atualizar(id, servicoRepository);
			return ResponseEntity.ok(new ServicoDto(servico));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Servico> optional = servicoRepository.findById(id);
		if(optional.isPresent()) {
			servicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	
}
