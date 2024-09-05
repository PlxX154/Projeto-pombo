package br.sc.senac.dw.projeto_pombo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.sc.senac.dw.projeto_pombo.model.entity.Pruu;

@Repository
public interface PruuRepository extends JpaRepository<Pruu, String>{

}
