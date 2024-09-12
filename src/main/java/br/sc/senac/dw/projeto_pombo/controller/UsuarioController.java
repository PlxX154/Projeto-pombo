package br.sc.senac.dw.projeto_pombo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.sc.senac.dw.projeto_pombo.exception.PomboException;
import br.sc.senac.dw.projeto_pombo.model.entity.Usuario;
import br.sc.senac.dw.projeto_pombo.service.UsuarioService;

@RestController
@RequestMapping(path = "/api/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	private List<Usuario> pesquisarTodos(){
		List<Usuario> tudo = usuarioService.pesquisarTodos();
		
		return tudo;
	};
	
	@GetMapping(path = "/{uuid}")
	private Usuario pesquisarPorUuid(@PathVariable String uuid) {
		return usuarioService.pesquisarPorUuid(uuid);
		
	}
	
	@PostMapping
	public ResponseEntity<Usuario> inserir(@RequestBody Usuario novo) throws PomboException {
		return ResponseEntity.ok(usuarioService.inserir(novo));
	}
	
	@PutMapping
	public Usuario atualizar(@RequestBody Usuario novovo) throws PomboException {
		return usuarioService.atualizar(novovo);
	}

}
