package br.sc.senac.dw.projeto_pombo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.sc.senac.dw.projeto_pombo.exception.PomboException;
import br.sc.senac.dw.projeto_pombo.model.entity.Pruu;
import br.sc.senac.dw.projeto_pombo.model.repository.PruuRepository;

@Service
public class PruuService {

	@Autowired
	private PruuRepository pruuRepository;

	public Pruu postar(Pruu novoPruu) throws PomboException {
		if(novoPruu.getPruuTexto() == null) {
			throw new PomboException("Texto n informado");
		}
		return pruuRepository.save(novoPruu);
	}
	
	
}