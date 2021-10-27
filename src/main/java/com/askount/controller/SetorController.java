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

import com.askount.controller.dto.SetorDto;
import com.askount.controller.form.SetorForm;
import com.askount.modelo.Setor;
import com.askount.repository.SetorRepository;
import com.askount.repository.UsuarioRepository;

@RestController
@RequestMapping("setor")
public class SetorController {

	@Autowired
	private SetorRepository setorRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public List<SetorDto> lista(Long idUsuario){
		if(idUsuario == null) {
			List<Setor> setor = setorRepository.findAll();
			return SetorDto.converter(setor);
		}else {
			List<Setor> setor = setorRepository.findByUsuarioId(idUsuario);
			return SetorDto.converter(setor);
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<SetorDto> cadastrar(@RequestBody @Valid SetorForm setorForm, UriComponentsBuilder uriBuilder){
		Setor setor = setorForm.converter(usuarioRepository);
		setorRepository.save(setor);
		
		URI uri = uriBuilder.path("/setor/{id}").buildAndExpand(setor.getId()).toUri();
		return ResponseEntity.created(uri).body(new SetorDto(setor));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SetorDto> detalhar(@PathVariable Long id){
		Optional<Setor> setor = setorRepository.findById(id);
		if(setor.isPresent()) {
			return ResponseEntity.ok(new SetorDto(setor.get()));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<SetorDto> atualizar(@PathVariable Long id, @RequestBody @Valid SetorForm setorForm) {
		Optional<Setor> optional = setorRepository.findById(id);
		if(optional.isPresent()) {
			Setor setor = setorForm.atualizar(id, setorRepository);
			return ResponseEntity.ok(new SetorDto(setor));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		Optional<Setor> optional = setorRepository.findById(id);
		if(optional.isPresent()) {
			setorRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
