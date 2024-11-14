package br.sc.senac.dw.projeto_pombo.service;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.sc.senac.dw.projeto_pombo.exception.PomboException;

import java.io.IOException;
import java.util.Base64;

@Service
public class ImagemService {

    public String processarImagem(MultipartFile file) throws PomboException{
        // Converte MultipartFile em byte[]
        byte[] imagemBytes;
		try {
			imagemBytes = file.getBytes();
		} catch (IOException e) {
			throw new PomboException("Erro ao processar arquivo");
		}
        
        // Converte byte[] para Base64
        String base64Imagem = Base64.getEncoder().encodeToString(imagemBytes);
        
        return base64Imagem;
    }
}
