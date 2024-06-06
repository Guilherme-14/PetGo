package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Veterinario;
import com.example.repository.VeterinarioRepository;

@Service
public class VeterinarioService {

	private final VeterinarioRepository veterinarioRepository;

	@Autowired
	public VeterinarioService(VeterinarioRepository veterinarioRepository) {
		this.veterinarioRepository = veterinarioRepository;
	}

	public List<Veterinario> getAllVeterinario() {
		return veterinarioRepository.findAll();
	}

	public Veterinario getVeterinarioById(Long id) {
		Optional<Veterinario> Veterinario = veterinarioRepository.findById(id);
		return Veterinario.orElse(null);
	}
	//Query Method 
	public List<Veterinario> buscarVeterinariosPorNome(String nome) {
		return veterinarioRepository.findByNome(nome); 
	}

	//Query Method
	public List<Veterinario> buscarVeterinariosPorDocumento(String documento){
		return veterinarioRepository.findByDocumento(documento);
	}

	public Veterinario salvarVeterinario(Veterinario Veterinario) {
		return veterinarioRepository.save(Veterinario);
	}

	public Veterinario updateVeterinario(Long id, Veterinario updatedVeterinario) {
		Optional<Veterinario> existingVeterinario = veterinarioRepository.findById(id);
		if (existingVeterinario.isPresent()) {
			updatedVeterinario.setId_veterinario(id);
			return veterinarioRepository.save(updatedVeterinario);
		}
		return null;
	}

	public boolean deleteVeterinario(Long id) {
		Optional<Veterinario> existingVeterinario = veterinarioRepository.findById(id);
		if (existingVeterinario.isPresent()) {
			veterinarioRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
