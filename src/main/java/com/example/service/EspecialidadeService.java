package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Especialidade;
import com.example.repository.EspecialidadeRepository;


@Service
public class EspecialidadeService {

private final EspecialidadeRepository especialidadeRepository;
	
	@Autowired
	public EspecialidadeService(EspecialidadeRepository EspecialidadeRepository) {
		this.especialidadeRepository = EspecialidadeRepository;
	}

	public List<Especialidade> buscaTodosespecialidade(){
		return especialidadeRepository.findAll();
	}

	public Especialidade buscaEspecialidadeId(Long id) {
		Optional <Especialidade> Especialidade = especialidadeRepository.findById(id);
		return Especialidade.orElse(null);
	}
	
	//metodo salvar as especialidade
	public Especialidade Salvaespecialidade(Especialidade especialidade) {
		return especialidadeRepository.save(especialidade);
	}
	
	public Especialidade alterarEspecialidade(Long id, Especialidade alterarEspecialidade) {
		Optional <Especialidade> existeEspecialidade = especialidadeRepository.findById(id);
		if (existeEspecialidade.isPresent()) {
			alterarEspecialidade.setId_especialidade(id);
			return especialidadeRepository.save(alterarEspecialidade);
		}
		return null;
	}
	public boolean apagarEspecialidade(Long id) {
		Optional <Especialidade> existeEspecialidade= especialidadeRepository.findById(id);
		if (existeEspecialidade.isPresent()) {
			especialidadeRepository.deleteById(id);
			return true;
		}
		return false;
	}


}
