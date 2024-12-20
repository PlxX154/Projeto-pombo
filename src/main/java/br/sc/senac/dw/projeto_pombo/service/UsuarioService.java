package br.sc.senac.dw.projeto_pombo.service;

import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.sc.senac.dw.projeto_pombo.exception.PomboException;
import br.sc.senac.dw.projeto_pombo.model.entity.Pruu;
import br.sc.senac.dw.projeto_pombo.model.entity.PruuCurtido;
import br.sc.senac.dw.projeto_pombo.model.entity.Usuario;
import br.sc.senac.dw.projeto_pombo.model.repository.PruuRepository;
import br.sc.senac.dw.projeto_pombo.model.repository.UsuarioRepository;
import br.sc.senac.dw.projeto_pombo.model.seletor.PruuSeletor;
import br.sc.senac.dw.projeto_pombo.model.seletor.UsuarioSeletor;

@Service
public class UsuarioService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PruuRepository pruuRepository;
	
	@Autowired
	private ImagemService imagemService;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username)
                .orElseThrow(
                	() -> new UsernameNotFoundException("Usuário não encontrado" + username)
                 );
    }
	
    public void salvarImagemUsuario(MultipartFile imagem, String idUsuario) throws PomboException{
    	Usuario usuarioComNovaImagem = usuarioRepository
    			.findById(idUsuario)
    			.orElseThrow(()-> new PomboException("Usuario não encontrado"));
    	
    	String imagemBase64 = imagemService.processarImagem(imagem);
    	
    	usuarioComNovaImagem.setImagemEmBase64(imagemBase64);
    	
    	usuarioRepository.save(usuarioComNovaImagem);
    }
    
	public Usuario inserir(Usuario novo) throws PomboException {
		validarCpfUsuario(novo);
		return usuarioRepository.save(novo);
	}
	
	private void validarCpfUsuario(Usuario novo) throws PomboException {
		
		String cpfNovo = novo.getCpf();
		Usuario cpfNovoUsuario = usuarioRepository.findByCpf(cpfNovo);
		
		if(cpfNovoUsuario != null) {
			throw new PomboException("cpf ja em uso");
		}
		
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

	public List<Usuario> pesquisarComSeletor(UsuarioSeletor seletor) {
		if(seletor.temPaginacao()) {
			int pageNumber = seletor.getPagina();
			int pageSize = seletor.getLimite();
			

			//Ler com atenção a documentação: 
			// @param pageNumber zero-based page number, must not be negative.
			// @param pageSize the size of the page to be returned, must be greater than 0.
			PageRequest pagina = PageRequest.of(pageNumber - 1, pageSize);
			return usuarioRepository.findAll(seletor, pagina).toList();
		}
		
		return usuarioRepository.findAll(seletor);
	}

	public int contarPaginas(UsuarioSeletor seletor) {
	    return (int) usuarioRepository.count(seletor);
    }

	public void excluir(String uuid) throws PomboException{
		
		
		long totalDePruusCriados = pruuRepository.countByIdUsuario(uuid);
		
		if(totalDePruusCriados > 0 ) {
			throw new PomboException("Usuario #" + uuid + "  já possui Pruus postados, logo não pode ser excluído.");
		}
		
		usuarioRepository.deleteById(uuid);
	}
	
}
