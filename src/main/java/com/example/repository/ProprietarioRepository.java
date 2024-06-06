package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.Proprietario;

public interface ProprietarioRepository extends JpaRepository<Proprietario,Long>{
	//Query Methods
	List<Proprietario>findByNome(String nome);
	List<Proprietario>findByCpf(String cpf); 
}
