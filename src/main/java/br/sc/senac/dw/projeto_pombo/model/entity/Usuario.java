package br.sc.senac.dw.projeto_pombo.model.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.sc.senac.dw.projeto_pombo.enums.PerfilAcesso;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	
    
    
    
    @Enumerated(EnumType.STRING)
	private PerfilAcesso perfil;

	// Métodos da interface UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        list.add(new SimpleGrantedAuthority(perfil.toString()));

        return list;
    }

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

}
