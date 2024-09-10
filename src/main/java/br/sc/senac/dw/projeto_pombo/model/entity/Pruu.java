package br.sc.senac.dw.projeto_pombo.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table
@Data
public class Pruu {

	@Id 
	@UuidGenerator 
	private String uuid; 
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario criadorDoPruu;
	
	@OneToMany(mappedBy = "pruu")
	private List<PruuCurtido> usuarios = new ArrayList<>();
	
	@Size(min = 1, max = 255)
	@NotBlank(message = "Texto é obrigatório")
	private String pruuTexto; 
	
	
	private int contagemCurtidas;
	
	@CreationTimestamp
	private LocalDateTime dataCriacao;
}
