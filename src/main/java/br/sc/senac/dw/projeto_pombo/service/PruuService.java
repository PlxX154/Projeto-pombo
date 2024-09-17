package br.sc.senac.dw.projeto_pombo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.sc.senac.dw.projeto_pombo.exception.PomboException;
import br.sc.senac.dw.projeto_pombo.model.entity.Pruu;
import br.sc.senac.dw.projeto_pombo.model.entity.PruuCurtido;
import br.sc.senac.dw.projeto_pombo.model.entity.Usuario;
import br.sc.senac.dw.projeto_pombo.model.repository.PruuCurtidoRepository;
import br.sc.senac.dw.projeto_pombo.model.repository.PruuRepository;
import br.sc.senac.dw.projeto_pombo.model.repository.UsuarioRepository;


@Service
public class PruuService {

	@Autowired
	private PruuRepository pruuRepository;
	
	@Autowired
	private PruuCurtidoRepository pruuCurtidoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Pruu postar(Pruu novoPruu) throws PomboException {
		if(novoPruu.getPruuTexto() == null) {
			throw new PomboException("Texto n informado");
		}
		if(novoPruu.getCriadorDoPruu() == null) {
			throw new PomboException("Usuario n informado");
		}
		return pruuRepository.save(novoPruu);
	}

	public List<Pruu> pesquisarTodos() {
		return pruuRepository.findAll();
	}

	public Pruu pesquisarPorUuid(String uuid) {
		return pruuRepository.findById(uuid).get();
	}

	public Pruu gostar(String uuidUsuarioQueCurtiu, String uuidPruu) {
		
		Pruu mensagem = pruuRepository.findById(uuidPruu).get();
		Usuario usuarioQueCurtiu = usuarioRepository.findById(uuidPruu).get();
		
		if(mensagem.getUsuariosCurtindo().contains(usuarioQueCurtiu)) {
			//USUÁRIO JÁ CURTIU
			
			//Diminuir a contagem de curtidas na mensagem
			int likes = 0;
			likes = mensagem.getContagemCurtidas();
			likes -= 1;
			mensagem.setContagemCurtidas(likes);
			
			//Chamar pruuRepository
			this.pruuCurtidoRepository.removerCurtidaDoPruu(usuarioQueCurtiu);
			
			
			//Remover curtida da lista usuariosCurtindo
			//Chamar pruuRepository?
		}
		
		
//		if(gostei.isJaCurtido() == false) {
//			int likes = 0;
//			likes = gostando.getContagemCurtidas();
//			likes += 1;
//			gostando.setContagemCurtidas(likes);
//			pruuRepository.save(gostando);
//		}else {
//			int likes = 0;
//			likes = gostando.getContagemCurtidas();
//			likes -= 1;
//			gostando.setContagemCurtidas(likes);
//			pruuRepository.delete(gostando);
//		}
		
		return mensagem;
	}
	
}