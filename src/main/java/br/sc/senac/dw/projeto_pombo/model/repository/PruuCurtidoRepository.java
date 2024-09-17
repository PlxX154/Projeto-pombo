package br.sc.senac.dw.projeto_pombo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.sc.senac.dw.projeto_pombo.model.entity.PruuCurtido;
import br.sc.senac.dw.projeto_pombo.model.entity.PruuCurtidoPK;
import br.sc.senac.dw.projeto_pombo.model.entity.Usuario;


@Repository
public interface PruuCurtidoRepository extends JpaRepository<PruuCurtido, PruuCurtidoPK>, JpaSpecificationExecutor<PruuCurtido> {

	//Exemplo com JPQL
	
	//DELETE FROM pruu_curtido
	//WHERE uuid_usuario = 'your_uuid_value'
	   //OR uuid_pruu = 'your_uuid_value';
	
	@Query("SELECT cp FROM CartaNaPartida cp WHERE "
			+ " cp.partida.id = :idPartida AND cp.carta.id = :idCarta")
	PruuCurtido removerCurtidaDoPruu(Usuario usuarioQueCurtiu);
	
}
