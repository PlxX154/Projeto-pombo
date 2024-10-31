package br.sc.senac.dw.projeto_pombo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.sc.senac.dw.projeto_pombo.model.entity.Pruu;
import br.sc.senac.dw.projeto_pombo.model.repository.PruuCurtidoRepository;
import br.sc.senac.dw.projeto_pombo.model.repository.PruuReportadoRepository;
import br.sc.senac.dw.projeto_pombo.model.repository.PruuRepository;


@SpringBootTest
@ActiveProfiles("test") 
public class PruuServiceTest {

	@Mock
	private PruuRepository ruuRepository;
	
	@Mock
	private PruuReportadoRepository pruuReportadoRepository;
	
	@Mock
	private PruuCurtidoRepository pruuCurtidoRepository;
	
	@InjectMocks
	private PruuService pruuService;
	
	private List<Pruu> pruus = new ArrayList();
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		carregarPruus();
	}
	
	private void carregarPruus() {
		pruus = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
        	Pruu pruu = new Pruu();
        	pruu.setUuid(i + 1 + "abc");
        	pruu.setDataCriacao(null);
        	pruu.setPruuTexto("texto numero: " + i);
        	
        }
	}
	
}
