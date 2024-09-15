package br.sc.senac.dw.projeto_pombo.service;

import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.sc.senac.dw.projeto_pombo.exception.PomboException;
import br.sc.senac.dw.projeto_pombo.model.entity.Pruu;
import br.sc.senac.dw.projeto_pombo.model.entity.PruuCurtido;
import br.sc.senac.dw.projeto_pombo.model.entity.Usuario;
import br.sc.senac.dw.projeto_pombo.model.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario inserir(Usuario novo) {
		return usuarioRepository.save(novo);
	}
	
	public List<Usuario> pesquisarTodos() {
		return usuarioRepository.findAll();
	}
	
	public Usuario pesquisarPorUuid(String uuid) {
		return usuarioRepository.findById(uuid).get();
	}
	
	public Usuario atualizar(Usuario usuarioAtualizado) throws PomboException {
		if(usuarioAtualizado.getUuid() == null) {
			throw new PomboException("informe um uuid valido!");
		}
		return usuarioRepository.save(usuarioAtualizado);
	}

	
	//pior q tem q ter coisa aqui
}
