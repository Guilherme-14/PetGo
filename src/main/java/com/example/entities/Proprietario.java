package com.example.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prorietario")
public class Proprietario {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 100)
    private Long id_prop;
    
    private String nome;
    
    private String cpf;
    
    private String rg;
    
    private String rua;
    
    private String cep;
    
    private String bairro;
    
    private String cidade;
    
    private String estado;
    
    private String telefone;
    
    
}
