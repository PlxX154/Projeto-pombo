package br.sc.senac.dw.projeto_pombo.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	
	@Size(min = 1, max = 300)
	@NotBlank(message = "Texto é obrigatório")
	private String pruuTexto; 
	
	private int contagemCurtidas;
	
	@Column(columnDefinition = "LONGTEXT")
	private String imagemEmBase64;
	
	//private boolean ativado;
	
	@CreationTimestamp
	private LocalDateTime dataCriacao;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario criadorDoPruu;
	
	@OneToMany(mappedBy = "pruu") 
	private List<PruuCurtido> curtidas = new ArrayList<>();
	
	@OneToMany(mappedBy = "pruu") 
	private List<PruuReportado> reportes = new ArrayList<>();
}
