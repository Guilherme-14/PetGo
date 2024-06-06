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

import com.example.entities.Pet;
import com.example.entities.Proprietario;
import com.example.service.ProprietarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag (name = "Proprietario", description = "API REST DE GERENCIAMENTO DE PROPRIETARIO")
@RestController
@RequestMapping("/proprietario")
public class ProprietarioController {

	private final ProprietarioService proprietarioService;

	@Autowired
	public ProprietarioController (ProprietarioService proprietarioService) {
		this.proprietarioService = proprietarioService;
	}

	//Query Method
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Proprietario>> buscarProprietarioPorNome(@PathVariable  String nome){
		List<Proprietario> proprietario = proprietarioService.buscarProprietarioPorNome(nome);
		return ResponseEntity.ok(proprietario);
	}
	
	//Query 
	@GetMapping("/cpf/{cpf}")
	public List<Proprietario> findProprietarioPorCpf(@PathVariable String cpf) {
		return proprietarioService.buscarProprietarioPorCpf(cpf);
	}

	@GetMapping("/{id}")
	@Operation (summary = "Localiza Proprietario por ID")
	public ResponseEntity<Proprietario> buscaProprietarioControlId(@PathVariable Long id){
		Proprietario Proprietario = proprietarioService.buscaProprietarioId(id);
		if(Proprietario != null) {
			return ResponseEntity.ok(Proprietario);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	@Operation (summary = "Apresenta todos os Proprietario")
	public ResponseEntity<List<Proprietario>> buscaTodosProprietarioControl(){
		List<Proprietario> Proprietarios = proprietarioService.buscaTodosProprietario();
		return ResponseEntity.ok(Proprietarios);
	}

	@PostMapping
	@Operation (summary = "Cadastra um Proprietario")
	public ResponseEntity<Proprietario> salvaProprietarioControl(@RequestBody @Valid Proprietario Proprietario){
		Proprietario salvaProprietario = proprietarioService.SalvaProprietario(Proprietario);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaProprietario);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Proprietario> alteraProprietarioControl(@PathVariable Long id, @RequestBody Proprietario Proprietario){
		Proprietario alterarProprietario = proprietarioService.alterarProprietario(id, Proprietario);
		if(Proprietario != null) {
			return ResponseEntity.ok(Proprietario);
		}
		else {
			return ResponseEntity.notFound().build();   	
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> apagarProprietarioControl(@PathVariable Long id){
		boolean apagar = proprietarioService.apagarProprietario(id);
		if (apagar) {
			return ResponseEntity.ok().body("O Proprietario foi excluido com sucesso");
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
