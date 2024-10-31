package br.sc.senac.dw.projeto_pombo.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.sc.senac.dw.projeto_pombo.model.entity.Pruu;
import br.sc.senac.dw.projeto_pombo.model.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>, JpaSpecificationExecutor<Usuario>{
	
	@Query("SELECT us FROM Usuario us WHERE us.cpf = :cpfNovo")
	Usuario findByCpf(String cpfNovo);

	//@Query("SELECT us FROM Usuario us WHERE us.email = :email")
	Optional<Usuario> findByEmail(String email);
	
}
