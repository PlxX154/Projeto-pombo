package br.sc.senac.dw.projeto_pombo.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class PruuCurtidoPK implements Serializable{
	
	@Column(name = "id_pruu")
	private String uuidPruu;
	
	@Column(name = "id_usuario")
	private String uuidUsuario;

}
