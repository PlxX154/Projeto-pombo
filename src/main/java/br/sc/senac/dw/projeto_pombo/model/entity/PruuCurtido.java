package br.sc.senac.dw.projeto_pombo.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;

@Entity
@Data
public class PruuCurtido {
	
	
	@EmbeddedId
	private PruuCurtidoPK id;	
	
	@JsonBackReference
	@ManyToOne
	@MapsId("uuidUsuario")
	@JoinColumn(name = "uuid_usuario")
	private Usuario usuario;
	
	
	@JsonBackReference
	@ManyToOne
	@MapsId("uuidPruu")
	@JoinColumn(name = "uuid_pruu")
	private Pruu pruu;
	
	@JoinColumn(name = "uuid_pruu")
	private boolean jaCurtido;
}