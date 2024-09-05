package br.sc.senac.dw.projeto_pombo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	public ResponseEntity<Pruu> inserir(@RequestBody Pruu novo)throws PomboException {
		return ResponseEntity.ok(pruuService.inserir(novo));
	}
	
}
