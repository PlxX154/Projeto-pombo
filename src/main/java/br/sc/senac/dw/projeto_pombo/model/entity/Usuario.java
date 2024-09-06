package br.sc.senac.dw.projeto_pombo.model.entity;

import java.util.List;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table
@Data
public class Usuario {

	@Id 
	@UuidGenerator 
	private String uuid;  
	
	@NotBlank(message = "Nome é obrigatório")
	@Size(min = 3, max = 255)
	private String nome;
	
	@CPF
	@NotBlank(message = "Cpf é obrigatório")
	private Integer cpf;
	
	
	@OneToMany(mappedBy = "criadorDoPruu")
	private List<Pruu> pruus;
	
	@Email
	@NotBlank(message = "Email é obrigatório")
	@Column(nullable = false)
	private String email;
	
	private String senha;
}
