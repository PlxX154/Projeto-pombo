package br.sc.senac.dw.projeto_pombo.model.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapsId;
import lombok.Data;

@Entity
@Data
public class PruuCurtido {

	@EmbeddedId
	private PruuCurtidoPK id;
	
	@ManyToMany
	@MapsId("pruuid")
	@JoinColumn(name = "id_pruu")
	Pruu pruu;
	
	
	@ManyToMany
	@MapsId("usuarioid")
	@JoinColumn(name = "id_usuario")
	Usuario usuario;
	
	private boolean pertenceAoUsuario;
}
