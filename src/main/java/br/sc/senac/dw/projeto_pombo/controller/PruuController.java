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
import br.sc.senac.dw.projeto_pombo.model.seletor.PruuSeletor;
import br.sc.senac.dw.projeto_pombo.service.PruuService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/pruu")
public class PruuController {

	@Autowired
	private PruuService pruuService;
	
	@Operation(summary = "Postar um Pruu novo", 
			   description = "Posta um Pruu novo")
	@PostMapping
	public Pruu postar(@RequestBody Pruu novoPruu) throws PomboException {
		return pruuService.postar(novoPruu);
	}
	
	@Operation(summary = "Pesquisar todos os Pruus", 
			   description = "Busca todos os pruus disponiveis")
	@GetMapping
	private List<Pruu> pesquisarTodos(){
		List<Pruu> tudo = pruuService.pesquisarTodos();
		
		return tudo;
	}
	
	@Operation(summary = "Pesquisar Pruus por UUID", 
			   description = "Busca um pruu específico pelo seu UUID.")
	@GetMapping(path = "/{uuid}")
	private Pruu pesquisarPorUuid(@PathVariable String uuid) {
		
		return pruuService.pesquisarPorUuid(uuid);
	}
	
	@Operation(summary = "Pesquisar Pruus com filtros", 
			   description = "Retorna uma lista de Pruus que atendem aos critérios especificados no seletor.")
	@PostMapping("/filtro")
	public List<Pruu> pesquisarComSeletor(@RequestBody PruuSeletor seletor){
		return pruuService.pesquisarComSeletor(seletor);
	}

	@Operation(summary = "Curtir um Pruu", 
			   description = "Curti um Pruu com uuid usuario e uuid pruu")
	@PutMapping(path = "/curtir")
	public Pruu gostar(String uuidUsuarioQueCurtiu, String uuidPruu) {
		return pruuService.gostar(uuidUsuarioQueCurtiu, uuidPruu);
	}
	
}
