package br.sc.senac.dw.projeto_pombo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping
	public ResponseEntity<Usuario> inserir(@RequestBody Usuario novo) throws PomboException {
		return ResponseEntity.ok(usuarioService.inserir(novo));
	}

}
