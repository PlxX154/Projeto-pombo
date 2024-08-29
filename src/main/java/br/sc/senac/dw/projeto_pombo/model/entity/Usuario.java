package br.sc.senac.dw.projeto_pombo.model.entity;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Usuario {

	@Id 
	@UuidGenerator
	private String uuid;  
	
	@NotBlank(message = "Nome é obrigatório")
	@Size(min = 3, max = 255)
	private String nome;
	
	@Column(nullable = false)
	private String email;
	
	private String senha;
	
	@CPF
	private Integer cpf;
	
	
}
