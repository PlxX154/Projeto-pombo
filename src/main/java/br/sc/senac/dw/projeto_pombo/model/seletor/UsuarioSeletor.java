package br.sc.senac.dw.projeto_pombo.model.seletor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.sc.senac.dw.projeto_pombo.model.entity.Usuario;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class UsuarioSeletor extends BaseSeletor implements Specification<Usuario>{
	
	
	private String nomeUsuario;
	private String email;
	private String cpf;


	public boolean temFiltro() {
		return  (this.stringValida(this.nomeUsuario)) 
			 || (this.stringValida(this.email)) 
			 || (this.stringValida(this.cpf));
	}

	
	@Override
	public Predicate toPredicate(Root<Usuario> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		
        if(this.getNomeUsuario() != null && this.getNomeUsuario().trim().length() > 0) {
            predicates.add(cb.like(root.get("nome"), "%" + this.getNomeUsuario() + "%"));
         }
        
        if(this.getEmail() != null && this.getEmail().trim().length() > 0) {
            predicates.add(cb.like(root.get("email"), "%" + this.getEmail() + "%"));
         }
        
        if(this.getCpf() != null && this.getCpf().trim().length() > 0) {
            predicates.add(cb.like(root.get("cpf"), "%" + this.getCpf() + "%"));
         }

        //usar caso necessario
        //aplicarFiltroIntervalo(root, cb, predicates, this.getLikesMinimo(), this.getLikesMaximo(), "contagemCurtidas");
        //aplicarFiltroPeriodo(root, cb, predicates, this.getDataInicioDaPostagem(), this.getDataFimDaPostagem(), "dataCriacao");
        
		return cb.and(predicates.toArray(new Predicate[0]));
	}

}
