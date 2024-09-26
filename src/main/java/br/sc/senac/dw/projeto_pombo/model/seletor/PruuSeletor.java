package br.sc.senac.dw.projeto_pombo.model.seletor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.sc.senac.dw.projeto_pombo.model.entity.Pruu;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;

@Data
public class PruuSeletor extends BaseSeletor implements Specification<Pruu>{
	
	
	private String nomeUsuario;
	private Integer likesMinimo;
	private Integer likesMaximo;
	
	//filtragem de datas por período (início, fim)
	private LocalDate dataInicioDaPostagem;
	private LocalDate dataFimDaPostagem;

	public boolean temFiltro() {
		return  (this.stringValida(this.nomeUsuario)) 
			 || (this.likesMinimo > 0)
			 || (this.likesMaximo > 0)
			 || (this.dataInicioDaPostagem != null)
			 || (this.dataFimDaPostagem != null);
	}

	
	@Override
	public Predicate toPredicate(Root<Pruu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		
        if(this.getNomeUsuario() != null && this.getNomeUsuario().trim().length() > 0) {
         	// WHERE/AND COLUNA OPERADOR VALOR
         	// WHERE      nome   like    '%Popó%'
            predicates.add(cb.like(root.get("criadorDoPruu").get("nome"), "%" + this.getNomeUsuario() + "%"));
         }
        
        
        aplicarFiltroIntervalo(root, cb, predicates, this.getLikesMinimo(), this.getLikesMaximo(), "contagemCurtidas");
        aplicarFiltroPeriodo(root, cb, predicates, this.getDataInicioDaPostagem(), this.getDataFimDaPostagem(), "dataCriacao");
        
		return cb.and(predicates.toArray(new Predicate[0]));
	}

}
