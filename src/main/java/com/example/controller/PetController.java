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

import com.example.entities.Pet;
import com.example.service.PetService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag (name = "Pet", description = "API REST DE GERENCIAMENTO DE PET")
@RestController
@RequestMapping("/pet")
public class PetController {

	 private final PetService petService;
	   
	   @Autowired
	   public PetController(PetService petService) {
	       this.petService = petService;
	   }
	   //Query Method
	   @GetMapping("/cidade/{cidade}")
	   public ResponseEntity<List<Pet>> buscarPetsPorNome(@PathVariable String nome) {
	     List<Pet> pets = petService.buscarPetsPorNome(nome);
	     return ResponseEntity.ok(pets);
	   }
	   
	   //@query
	   @GetMapping("/raca/{raca}")
	   public List<Pet> findPetsPorRaca(@PathVariable String raca) {
	       return petService.buscarPetsPorRaca(raca);
	   }
	  
	   @GetMapping("/{id}")
	   public ResponseEntity<Pet> getProductById(@PathVariable Long id) {
	       Pet pet = petService.getPetById(id);
	       if (pet != null) {
	           return ResponseEntity.ok(pet);
	       } else {
	           return ResponseEntity.notFound().build();
	       }
	   }

	   
	   public ResponseEntity<List<Pet>> getAllPet() {
	       List<Pet> pet = petService.getAllPet();
	       return ResponseEntity.ok(pet);
	   }

	   @PostMapping("/")
	   public ResponseEntity<Pet> criarPet(@RequestBody @Valid Pet pet) {
	       Pet criarPet = petService.salvarPet(pet);
	       return ResponseEntity.status(HttpStatus.CREATED).body(criarPet);
	   }
	  

	   @PutMapping("/{id}")
	   public ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestBody @Valid Pet pet) {
	       Pet updatedPet = petService.updatePet(id, pet);
	       if (updatedPet != null) {
	           return ResponseEntity.ok(updatedPet);
	       } else {
	           return ResponseEntity.notFound().build();
	       }
	   }

	   @DeleteMapping("/{id}")
	   public ResponseEntity<String> deletePet(@PathVariable Long id) {
	       boolean deleted = petService.deletePet(id);
	       if (deleted) {
	        return ResponseEntity.ok().body("A Turma foi excluído com sucesso.");  
	       } else {
	           return ResponseEntity.notFound().build();
	       }
	   }
}
