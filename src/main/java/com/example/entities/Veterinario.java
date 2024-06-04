package com.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "veterinario")
public class Veterinario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 100)
    private Long id_veterinario;
    
    private String nome;
    
    private String tipo_pet;
    
    private String raca;
    
    private String cor;
    
    private String documento;
    
    private String data_nascimento;
    
    @ManyToOne
    @JoinColumn(name = "id_prop")
    private Proprietario proprietario;
    
    @ManyToOne
    @JoinColumn(name = "id_especialidade")
    private Especialidade especialidade;
    
}
