package br.sc.senac.dw.projeto_pombo.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.sc.senac.dw.projeto_pombo.model.entity.PruuCurtido;
import br.sc.senac.dw.projeto_pombo.model.entity.PruuCurtidoPK;

@Repository
public interface PruuCurtidoRepository extends JpaRepository<PruuCurtido, PruuCurtidoPK>, JpaSpecificationExecutor<PruuCurtido> {

	//Exemplo com JPQL
	//@Query("SELECT cp FROM CartaNaPartida cp WHERE "
	//		+ " cp.partida.id = :idPartida AND cp.carta.id = :idCarta")
	//CartaNaPartida findByJogada(int idPartida, int idCarta);
	
}
