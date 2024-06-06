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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Especialidade;
import com.example.service.EspecialidadeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag (name = "Especialidade", description = "API REST DE GERENCIAMENTO DE ESPECIALIDADE")
@RestController
@RequestMapping("/Especialidade")
public class EspecialidadeController {

	private final EspecialidadeService especialidadeService;
	
	@Autowired
	public EspecialidadeController (EspecialidadeService especialidadeService) {
		this.especialidadeService = especialidadeService;
	}
	@GetMapping("/{id}")
	@Operation (summary = "Localiza Especialidade por ID")
	public ResponseEntity<Especialidade> buscaEspecialidadeControlId(@PathVariable Long id){
		Especialidade Especialidade = especialidadeService.buscaEspecialidadeId(id);
		if(Especialidade != null) {
			return ResponseEntity.ok(Especialidade);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping
	@Operation (summary = "Apresenta todos os Especialidade")
	public ResponseEntity<List<Especialidade>> buscaTodosEspecialidadeControl(){
		List<Especialidade> Especialidade = especialidadeService.buscaTodosespecialidade();
		return ResponseEntity.ok(Especialidade);
	}
	@PostMapping
	@Operation (summary = "Cadastra um Especialidade")
	public ResponseEntity<Especialidade> salvaEspecialidadeControl(@RequestBody @Valid Especialidade especialidade){
		Especialidade salvaEspecialidade = especialidadeService.Salvaespecialidade(especialidade);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaEspecialidade);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Especialidade> alteraEspecialidadeControl(@PathVariable Long id, @RequestBody Especialidade especialidade){
		Especialidade alterarEspecialidade = especialidadeService.alterarEspecialidade(id, especialidade);
		if(especialidade != null) {
			return ResponseEntity.ok(especialidade);
		}
		else {
			return ResponseEntity.notFound().build();   
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> apagarEspecialidadeControl(@PathVariable Long id){
		boolean apagar = especialidadeService.apagarEspecialidade(id);
		if (apagar) {
			return ResponseEntity.ok().body("O Especialidade foi excluido com sucesso");
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
