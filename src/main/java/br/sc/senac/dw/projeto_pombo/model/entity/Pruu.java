package br.sc.senac.dw.projeto_pombo.model.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Entity
@Table
@Data
public class Pruu {

	@Id
	private Integer id;
	
	
	private Usuario usuario;
	
	
	@Min(1)
	@Max(300)
	private String PruuTexto;
	
	
	private int Curtidas;
	
	private LocalDateTime data;
}
