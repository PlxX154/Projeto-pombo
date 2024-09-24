package br.sc.senac.dw.projeto_pombo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.sc.senac.dw.projeto_pombo.exception.PomboException;
import br.sc.senac.dw.projeto_pombo.model.entity.Pruu;
import br.sc.senac.dw.projeto_pombo.model.entity.PruuCurtido;
import br.sc.senac.dw.projeto_pombo.service.PruuService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/pruu")
public class PruuController {

	@Autowired
	private PruuService pruuService;
	
	@PostMapping
	public Pruu postar(@RequestBody Pruu novoPruu) throws PomboException {
		return pruuService.postar(novoPruu);
	}
	
	@GetMapping
	private List<Pruu> pesquisarTodos(){
		List<Pruu> tudo = pruuService.pesquisarTodos();
		
		return tudo;
	}
	
	@Operation(summary = "Pesquisar pruu por UUID", 
			   description = "Busca um pruu específico pelo seu UUID.")
	@GetMapping(path = "/{uuid}")
	private Pruu pesquisarPorUuid(@PathVariable String uuid) {
		
		return pruuService.pesquisarPorUuid(uuid);
	}

	@PutMapping(path = "/curtir")
	public Pruu gostar(String uuidUsuarioQueCurtiu, String uuidPruu) {
		return pruuService.gostar(uuidUsuarioQueCurtiu, uuidPruu);
	}
	
}
