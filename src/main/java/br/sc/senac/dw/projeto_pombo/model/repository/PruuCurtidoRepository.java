package br.sc.senac.dw.projeto_pombo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.sc.senac.dw.projeto_pombo.model.entity.PruuCurtido;
import br.sc.senac.dw.projeto_pombo.model.entity.PruuCurtidoPK;



@Repository
public interface PruuCurtidoRepository extends JpaRepository<PruuCurtido, PruuCurtidoPK>, JpaSpecificationExecutor<PruuCurtido> {

	//Exemplo com JPQL
	@Query("SELECT pc FROM PruuCurtido pc WHERE "
			+ " pc.pruu.uuid = :uuidPruu AND pc.usuario.uuid = :uuidUsuario")
	PruuCurtido findByCurtida(String uuidPruu , String uuidUsuario);
	
}
