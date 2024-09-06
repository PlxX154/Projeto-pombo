package br.sc.senac.dw.projeto_pombo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.sc.senac.dw.projeto_pombo.exception.PomboException;
import br.sc.senac.dw.projeto_pombo.model.entity.Pruu;
import br.sc.senac.dw.projeto_pombo.service.PruuService;

@RestController
@RequestMapping(path = "/api/pruu")
public class PruuController {

	@Autowired
	private PruuService pruuService;
	
	@PostMapping
	public Pruu postar(@RequestBody Pruu novoPruu)throws PomboException {
		return pruuService.postar(novoPruu);
	}
	
}
