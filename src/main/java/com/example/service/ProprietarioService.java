package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Proprietario;
import com.example.repository.ProprietarioRepository;

@Service
public class ProprietarioService {

	private final ProprietarioRepository proprietarioRepository;

	@Autowired
	public ProprietarioService(ProprietarioRepository proprietarioRepository) {
		this.proprietarioRepository = proprietarioRepository;
	}

	// query method
	public List<Proprietario> buscarProprietarioPorNome(String nome) {
		return proprietarioRepository.findByNome(nome);
	}

	// query method
	public List<Proprietario> buscarProprietarioPorCpf(String cpf) {
		return proprietarioRepository.findByCpf(cpf);
	}

	public List<Proprietario> buscaTodosProprietario() {
		return proprietarioRepository.findAll();
	}

	public Proprietario buscaProprietarioId(Long id) {
		Optional<Proprietario> Proprietario = proprietarioRepository.findById(id);
		return Proprietario.orElse(null);
	}

	// metodo salvar as Equipe
	public Proprietario SalvaProprietario(Proprietario proprietario) {
		return proprietarioRepository.save(proprietario);
	}

	public Proprietario alterarProprietario(Long id, Proprietario alterarProprietario) {
		Optional<Proprietario> existeProprietario = proprietarioRepository.findById(id);

		if (existeProprietario.isPresent()) {
			alterarProprietario.setId_prop(id);
			return proprietarioRepository.save(alterarProprietario);
		}
		return null;
	}

	public boolean apagarProprietario(Long id) {
		Optional<Proprietario> existeProprietario = proprietarioRepository.findById(id);
		if (existeProprietario.isPresent()) {
			proprietarioRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
