package br.sc.senac.dw.projeto_pombo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import br.sc.senac.dw.projeto_pombo.exception.PomboException;
import br.sc.senac.dw.projeto_pombo.model.entity.Usuario;
import br.sc.senac.dw.projeto_pombo.model.repository.UsuarioRepository;

@Service
public class AuthenticationService {

    private final JwtService jwtService;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public AuthenticationService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public String authenticate(Authentication authentication) {
        return jwtService.getGenerateToken(authentication);
    }
    
    public Usuario getUsuarioAutenticado() throws PomboException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioAutenticado = null;
        
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            
            Jwt jwt = (Jwt) principal;
            String login = jwt.getClaim("sub");
            
            usuarioAutenticado = usuarioRepository.findByEmail(login)
            									  .orElseThrow(() -> new PomboException("Usuário não encontrado"));
        }
        
        if(usuarioAutenticado == null) {
			throw new PomboException("Usuário não encontrado");
		}
        
        return usuarioAutenticado;
    }
}
