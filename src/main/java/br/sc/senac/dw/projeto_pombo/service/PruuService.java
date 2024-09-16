package br.sc.senac.dw.projeto_pombo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.sc.senac.dw.projeto_pombo.exception.PomboException;
import br.sc.senac.dw.projeto_pombo.model.entity.Pruu;
import br.sc.senac.dw.projeto_pombo.model.entity.PruuCurtido;
import br.sc.senac.dw.projeto_pombo.model.repository.PruuCurtidoRepository;
import br.sc.senac.dw.projeto_pombo.model.repository.PruuRepository;

@Service
public class PruuService {

	@Autowired
	private PruuRepository pruuRepository;

	public Pruu postar(Pruu novoPruu) throws PomboException {
		if(novoPruu.getPruuTexto() == null) {
			throw new PomboException("Texto n informado");
		}
		if(novoPruu.getCriadorDoPruu() == null) {
			throw new PomboException("Usuario n informado");
		}
		return pruuRepository.save(novoPruu);
	}

	public List<Pruu> pesquisarTodos() {
		return pruuRepository.findAll();
	}

	public Pruu pesquisarPorUuid(String uuid) {
		return pruuRepository.findById(uuid).get();
	}

	public Pruu gostar(Pruu gostando, PruuCurtido gostei) {
		if(gostei.isJaCurtido() == false) {
			int likes = 0;
			likes = gostando.getContagemCurtidas();
			likes += 1;
			gostando.setContagemCurtidas(likes);
		}else {
			
		}
		return pruuRepository.save(gostando);
		
		}
	
}