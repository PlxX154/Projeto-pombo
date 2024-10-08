package br.sc.senac.dw.projeto_pombo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.sc.senac.dw.projeto_pombo.model.entity.PruuCurtido;
import br.sc.senac.dw.projeto_pombo.model.entity.PruuReportado;
import br.sc.senac.dw.projeto_pombo.model.entity.PruuReportadoPK;

@Repository
public interface PruuReportadoRepository extends JpaRepository<PruuReportado, PruuReportadoPK>, JpaSpecificationExecutor<PruuReportado> {

	@Query("SELECT pr FROM PruuReportado pr WHERE "
			+ " pr.pruu.uuid = :uuidPruu AND pr.usuario.uuid = :uuidUsuario")
	PruuReportado findByReporte(String uuidPruu , String uuidUsuario);
}
