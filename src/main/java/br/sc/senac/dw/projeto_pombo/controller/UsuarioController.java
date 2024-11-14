package br.sc.senac.dw.projeto_pombo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.sc.senac.dw.projeto_pombo.auth.AuthenticationService;
import br.sc.senac.dw.projeto_pombo.enums.PerfilAcesso;
import br.sc.senac.dw.projeto_pombo.exception.PomboException;
import br.sc.senac.dw.projeto_pombo.model.entity.Usuario;
import br.sc.senac.dw.projeto_pombo.model.seletor.UsuarioSeletor;
import br.sc.senac.dw.projeto_pombo.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping(path = "/api/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private AuthenticationService authService;
	
	@Operation(
			summary = "Upload de Imagem para Carta",
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
				description = "Arquivo de imagem a ser enviado",
				required = true,
				content = @Content(
					mediaType = "multipart/form-data",
					schema = @Schema(type = "string", format = "binary")
				)
			),
			description = "Realiza o upload de uma imagem associada a uma carta específica."
		)
		@PostMapping("/{id}/upload")
	public void fazerUploadUsuario(@RequestParam("imagem")MultipartFile imagem, @PathVariable String uuid) throws PomboException{
		
		if(imagem == null) {
			throw new PomboException("Arquivo inválido");
		}
		
		Usuario usuarioAutenticado = authService.getUsuarioAutenticado();
		
		if(usuarioAutenticado == null) {
			throw new PomboException("usuario não encontrado");
		}
		
		if(usuarioAutenticado.getPerfil() == PerfilAcesso.USUARIO) {
			throw new PomboException("Usuário sem permissão de acesso");
		}
		
		usuarioService.salvarImagemUsuario(imagem, uuid);
	}
	
	@Operation(summary = "Pesquisar Todos os usuarios", 
			   description = "Retorna uma lista de todos os Usuarios cadastrados.")
	@GetMapping
	private List<Usuario> pesquisarTodos(){
		List<Usuario> tudo = usuarioService.pesquisarTodos();
		return tudo;
	};
	
	@Operation(summary = "Pesquisar por uuid", 
			   description = "Retorna uma lista de Usuarios cadastrados por uuid.")
	@GetMapping(path = "/{uuid}")
	private Usuario pesquisarPorUuid(@PathVariable String uuid) {
		return usuarioService.pesquisarPorUuid(uuid);
		
	}
	
	@Operation(summary = "Registrar usuario", 
			   description = "Registra novos usuarios no banco")
	@PostMapping
	public ResponseEntity<Usuario> inserir(@RequestBody Usuario novovo) throws PomboException {
		return ResponseEntity.ok(usuarioService.inserir(novovo));
	}
	
	@Operation(summary = "atualizar usuario", 
			   description = "atualiza usuario ja existentes no banco")
	@PutMapping
	public Usuario atualizar(@RequestBody Usuario novo) throws PomboException {
		return usuarioService.atualizar(novo);
	}
	
	@Operation(summary = "Pesquisar Usuarios com filtros", 
			   description = "Retorna uma lista de Usuarios que atendem aos critérios especificados no seletor.")
	@PostMapping("/filtro")
	public List<Usuario> pesquisarComSeletor(@RequestBody UsuarioSeletor seletor){
		return usuarioService.pesquisarComSeletor(seletor);
	}
	
	@Operation(summary = "Deletar Usuarios por uuid", 
			   description = "Deleta usuario dado o seu uuid, desde q o usuario n tenha pruus postados")
	@DeleteMapping("/{uuid}")
	public ResponseEntity<Void> excluir(@PathVariable String uuid) throws PomboException {
		usuarioService.excluir(uuid);
		return ResponseEntity.noContent().build();
	}
	
}
