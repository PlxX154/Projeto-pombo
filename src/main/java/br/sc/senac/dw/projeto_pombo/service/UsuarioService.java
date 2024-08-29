package br.sc.senac.dw.projeto_pombo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.sc.senac.dw.projeto_pombo.model.entity.Usuario;
import br.sc.senac.dw.projeto_pombo.model.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario inserir(Usuario novo) {
		return usuarioRepository.save(novo);
	}
	
	//tem porra nenhuma aqui n
}
