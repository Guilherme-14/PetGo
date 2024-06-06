package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Proprietario;
import com.example.entities.Veterinario;
import com.example.service.VeterinarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag (name = "Veterinario", description = "API REST DE GERENCIAMENTO DE VETERINARIO")
@RestController
@RequestMapping("/veterinario")
public class VeterinarioController {

	private final VeterinarioService veterinarioService;

	@Autowired
	public VeterinarioController (VeterinarioService veterinarioService) {
		this.veterinarioService = veterinarioService;
	}

	//Query Method
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Veterinario>> buscarVeterinarioPorNome(@PathVariable  String nome){
		List<Veterinario> Veterinario = veterinarioService.buscarVeterinariosPorNome(nome);
		return ResponseEntity.ok(Veterinario);
	}
	//Query 
		@GetMapping("/documento/{documento}")
		public List<Veterinario> findVeterinarioPorDocumento(@PathVariable String documento) {
			return veterinarioService.buscarVeterinariosPorDocumento(documento);
	}

	@GetMapping("/{id}")
	@Operation (summary = "Localiza Veterinario por ID")
	public ResponseEntity<Veterinario> buscaVeterinarioControlId(@PathVariable Long id){
		Veterinario Veterinario = veterinarioService.getVeterinarioById(id);
		if(Veterinario != null) {
			return ResponseEntity.ok(Veterinario);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	@Operation (summary = "Apresenta todos os Veterinario")
	public ResponseEntity<List<Veterinario>> buscaTodosVeterinarioControl(){
		List<Veterinario> Veterinarios = veterinarioService.getAllVeterinario();
		return ResponseEntity.ok(Veterinarios);
	}

	@PostMapping
	@Operation (summary = "Cadastra um Veterinario")
	public ResponseEntity<Veterinario> salvaVeterinarioControl(@RequestBody @Valid Veterinario veterinario){
		Veterinario salvaVeterinario = veterinarioService.salvarVeterinario(veterinario);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaVeterinario);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Veterinario> alteraVeterinarioControl(@PathVariable Long id, @RequestBody Veterinario veterinario){
		Veterinario alterarVeterinario = veterinarioService.updateVeterinario(id, veterinario);
		if(veterinario != null) {
			return ResponseEntity.ok(veterinario);
		}
		else {
			return ResponseEntity.notFound().build();   	
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> apagarVeterinarioControl(@PathVariable Long id){
		boolean apagar = veterinarioService.deleteVeterinario(id);
		if (apagar) {
			return ResponseEntity.ok().body("O Veterinario foi excluido com sucesso");
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
