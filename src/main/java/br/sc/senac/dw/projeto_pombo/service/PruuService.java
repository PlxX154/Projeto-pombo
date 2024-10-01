package br.sc.senac.dw.projeto_pombo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.sc.senac.dw.projeto_pombo.exception.PomboException;
import br.sc.senac.dw.projeto_pombo.model.entity.Pruu;
import br.sc.senac.dw.projeto_pombo.model.entity.PruuCurtido;
import br.sc.senac.dw.projeto_pombo.model.entity.PruuCurtidoPK;
import br.sc.senac.dw.projeto_pombo.model.entity.Usuario;
import br.sc.senac.dw.projeto_pombo.model.repository.PruuCurtidoRepository;
import br.sc.senac.dw.projeto_pombo.model.repository.PruuRepository;
import br.sc.senac.dw.projeto_pombo.model.repository.UsuarioRepository;
import br.sc.senac.dw.projeto_pombo.model.seletor.PruuSeletor;


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

	public Pruu gostar(String uuidUsuarioQueCurtiu, String uuidPruu) throws PomboException {
		
		if(!pruuRepository.findById(uuidPruu).isPresent()) {
			throw new PomboException("Mensagem n informado ou invalido");
		}
		
		if(!pruuRepository.findById(uuidUsuarioQueCurtiu).isPresent()) {
			throw new PomboException("Usuario n informado ou invalido");
		}
		Pruu mensagem = pruuRepository.findById(uuidPruu).get(); 
		Usuario usuarioQueCurtiu = usuarioRepository.findById(uuidUsuarioQueCurtiu).get();
		
		
		PruuCurtido curtida = this.pruuCurtidoRepository.findByCurtida(uuidPruu, uuidUsuarioQueCurtiu);
		
		if(curtida == null) {
			//CRIAR NOVA CURTIDA
			int likes = 0;
			likes = mensagem.getContagemCurtidas();
			likes += 1;
			mensagem.setContagemCurtidas(likes);
			
			PruuCurtido novoLike = new PruuCurtido();
			PruuCurtidoPK novoId = new PruuCurtidoPK();
			novoId.setUuidPruu(uuidPruu);
			novoId.setUuidUsuario(uuidUsuarioQueCurtiu);
			
			novoLike.setId(novoId);
			novoLike.setUsuario(usuarioQueCurtiu);
			novoLike.setPruu(mensagem);
			
			this.pruuCurtidoRepository.save(novoLike); //INSERT de nova curtida (em PruuCurtido)
			this.pruuRepository.save(mensagem);        //UPDATE da mensagem (Pruu)
		}else {
			//USUÁRIO JÁ CURTIU
			
			//Diminuir a contagem de curtidas na mensagem
			//Chamar pruuRepository
			int likes = 0;
			likes = mensagem.getContagemCurtidas();
			likes -= 1;
			mensagem.setContagemCurtidas(likes);
			
			//Remover curtida da lista usuariosCurtindo
			//Chamar pruuRepository?

			this.pruuCurtidoRepository.delete(curtida);
			this.pruuRepository.save(mensagem);        //UPDATE da mensagem (Pruu)
		}
		
		return mensagem;
	}

	public List<Pruu> pesquisarComSeletor(PruuSeletor seletor) {
		if(seletor.temPaginacao()) {
			int pageNumber = seletor.getPagina();
			int pageSize = seletor.getLimite();
			

			//Ler com atenção a documentação: 
			// @param pageNumber zero-based page number, must not be negative.
			// @param pageSize the size of the page to be returned, must be greater than 0.
			PageRequest pagina = PageRequest.of(pageNumber - 1, pageSize);
			return pruuRepository.findAll(seletor, pagina).toList();
		}
		
		return pruuRepository.findAll(seletor);
	}
	
	public int contarPaginas(PruuSeletor seletor) {
		return (int) pruuRepository.count(seletor);
	}

	public List<PruuCurtido> pesquisarTodosOsLikes() {
		return pruuCurtidoRepository.findAll();
	}
	
}