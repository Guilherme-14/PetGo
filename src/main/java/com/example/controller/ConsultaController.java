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

import com.example.entities.Consulta;
import com.example.service.ConsultaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag (name = "Consulta", description = "API REST DE GERNECIAMENTO DE CONSULTA")
@RestController
@RequestMapping("/consulta")
public class ConsultaController {

private final ConsultaService consultaService;
	
	@Autowired
	public ConsultaController (ConsultaService consultaService) {
		this.consultaService = consultaService;
	}
	
	@GetMapping("/{id}")
	@Operation (summary = "Localiza Consulta por ID")
	public ResponseEntity<Consulta> buscaConsultaControlId(@PathVariable Long id){
		Consulta Consulta = consultaService.buscaConsultaId(id);
		if(Consulta != null) {
			return ResponseEntity.ok(Consulta);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	@Operation (summary = "Apresenta todos os Consulta")
	public ResponseEntity<List<Consulta>> buscaTodosConsultaControl(){
		List<Consulta> Consulta = consultaService.buscaTodosConsulta();
		return ResponseEntity.ok(Consulta);
	}
	
	@PostMapping
	@Operation (summary = "Cadastra um Consulta")
	public ResponseEntity<Consulta> salvaConsultaControl(@RequestBody @Valid Consulta consulta){
		Consulta salvaConsulta = consultaService.salvaConsulta(consulta);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaConsulta);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Consulta> alteraConsultaControl(@PathVariable Long id, @RequestBody Consulta consulta){
		Consulta alterarConsulta = consultaService.alterarConsulta(id, consulta);
		if(consulta != null) {
			return ResponseEntity.ok(consulta);
		}
		else {
			return ResponseEntity.notFound().build();   	
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> apagarConsultaControl(@PathVariable Long id){
		boolean apagar = consultaService.apagarConsulta(id);
		if (apagar) {
			return ResponseEntity.ok().body("O Consulta foi excluido com sucesso");
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
}
