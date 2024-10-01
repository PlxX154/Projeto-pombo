package br.sc.senac.dw.projeto_pombo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.sc.senac.dw.projeto_pombo.model.entity.Pruu;

@Repository
public interface PruuRepository extends JpaRepository<Pruu, String>, JpaSpecificationExecutor<Pruu>{

    @Query("SELECT COUNT(pr) FROM Pruu pr WHERE pr.criadorDoPruu.uuid = :uuidUsuario")
	long countByIdUsuario(String uuidUsuario);
	
}
