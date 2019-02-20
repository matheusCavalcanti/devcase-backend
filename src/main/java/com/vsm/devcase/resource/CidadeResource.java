package com.vsm.devcase.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsm.devcase.model.Cidade;
import com.vsm.devcase.service.CidadeService;

@RestController
@RequestMapping("cidades")
public class CidadeResource {

	@Autowired
	private CidadeService service;
	
	@GetMapping
	public ResponseEntity<List<Cidade>> listarCidades() {
		List<Cidade> cidades = service.listarTodasCidades();
		return ResponseEntity.ok().body(cidades);
	}
	
	@PostMapping
	//@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Cidade> novaCidade(@Valid @RequestBody Cidade cidade) {
		Cidade cidadeSalva = service.salvarNovaCidade(cidade);
		return ResponseEntity.ok().body(cidadeSalva);
	}
	
	@DeleteMapping("/{id}")
	//@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Void> excluirCidade(@PathVariable Long id) {
		service.excluirCidade(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarCidadePorId(@PathVariable Long id) {
		Optional<Cidade> cidade = service.buscarCidadePorId(id);
		return ResponseEntity.of(cidade);
	}
	
	@PutMapping("/{id}")
	//@PreAuthorize("hasAuthority('admin')")
	public ResponseEntity<Cidade> atualizarCidade(@PathVariable Long id, @Valid @RequestBody Cidade cidade) {
		Cidade cidadeAtualizada = service.atualizarCidade(id, cidade);
		return ResponseEntity.ok().body(cidadeAtualizada);
	}
	
}
