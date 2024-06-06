package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Pet;

public interface PetRepository extends JpaRepository<Pet,Long>{
	List<Pet>findByNome(String nome);
	List<Pet>findByRaca(String raca);
}
