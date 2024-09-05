package br.sc.senac.dw.projeto_pombo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.sc.senac.dw.projeto_pombo.model.entity.Pruu;
import br.sc.senac.dw.projeto_pombo.model.repository.PruuRepository;

@Service
public class PruuService {

	@Autowired
	private PruuRepository pruuRepository;

	public Object inserir(Pruu novo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
