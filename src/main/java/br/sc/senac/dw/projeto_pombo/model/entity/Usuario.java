package br.sc.senac.dw.projeto_pombo.model.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
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
	private String cpf;
	
	@Email
	@NotBlank(message = "Email é obrigatório")
	private String email;
	
	private String senha;
	
    @JsonBackReference(value ="criadorDoPruu")
	@OneToMany(mappedBy = "criadorDoPruu")
	private List<Pruu> pruusPostados;
	
	
    @JsonBackReference(value ="usuarioCurtindo")
	@OneToMany(mappedBy = "usuario")
	private List<PruuCurtido> pruusCurtidos = new ArrayList<>();
	

}
