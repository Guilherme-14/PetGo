package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Veterinario;

public interface VeterinarioRepository  extends JpaRepository<Veterinario,Long>{
	List<Veterinario>findByNome(String nome);
	List<Veterinario>findByDocumento(String documento);

}
