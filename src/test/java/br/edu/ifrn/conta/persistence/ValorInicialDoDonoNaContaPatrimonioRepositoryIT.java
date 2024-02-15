package br.edu.ifrn.conta.persistence;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.domain.ContaPatrimonio;
import br.edu.ifrn.conta.domain.Dono;
import br.edu.ifrn.conta.domain.ValorInicialDoDonoNaContaPatrimonio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ValorInicialDoDonoNaContaPatrimonioRepositoryIT {

	@Autowired
	private ValorInicialDoDonoNaContaPatrimonioRepository valorInicialDoDonoNaContaPatrimonioRepository;

	@Autowired
	private DonoFactory donoFactory;

	@Autowired
	private ValorInicialDoDonoNaContaPatrimonioFactory valorInicialDoDonoNaContaPatrimonioFactory;

	@Autowired
	private ContaPatrimonioFactory contaPatrimonioFactory;

	@Test
	public void repositorioNaoEhNulo() {
		assertThat(this.valorInicialDoDonoNaContaPatrimonioRepository).isNotNull();
	}

	@Test
	public void findByDonoAndContaPatrimonio() {
		// cria o ambiente de teste
		Dono papai = this.donoFactory.papai();

		ContaPatrimonio contaPatrimonio = this.contaPatrimonioFactory.poupanca();

		ValorInicialDoDonoNaContaPatrimonio valorInicialDoDonoNaContaPatrimonio = this.valorInicialDoDonoNaContaPatrimonioFactory
			.valorInicialDoDonoNaContaPatrimonio(papai, contaPatrimonio, new BigDecimal(100));

		// executa a operacao a ser testada
		// verifica o efeito da execucao da operacao a ser testada
		assertThat(
				this.valorInicialDoDonoNaContaPatrimonioRepository.findByDonoAndContaPatrimonio(papai, contaPatrimonio)
					.get())
			.isEqualTo(valorInicialDoDonoNaContaPatrimonio);
	}

}
