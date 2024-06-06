package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Consulta;
import com.example.repository.ConsultaRepository;

@Service
public class ConsultaService {

	private final ConsultaRepository consultaRepository;
	
	@Autowired
	public ConsultaService(ConsultaRepository consultaRepository) {
		this.consultaRepository = consultaRepository;
	}
	public  List<Consulta> buscaTodosConsulta(){
		return consultaRepository.findAll();
	}

	public Consulta buscaConsultaId(Long id) {
		Optional <Consulta> Consulta = consultaRepository.findById(id);
		return Consulta.orElse(null);
	}
	public Consulta salvaConsulta(Consulta consulta){
		return consultaRepository.save(consulta);
	}
	
	public Consulta alterarConsulta(Long id, Consulta alterarConsulta) {
		Optional <Consulta> existeConsulta = consultaRepository.findById(id);
		if (existeConsulta.isPresent()) {
			alterarConsulta.setId_consulta(id);
			return consultaRepository.save(alterarConsulta);
		}
		return null;
	}
	public boolean apagarConsulta (Long id) {
		Optional <Consulta> existeConsulta = consultaRepository.findById(id);
		if (existeConsulta.isPresent()) {
			consultaRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
