package br.edu.ifrn.conta.persistence;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.domain.ContaCredito;
import br.edu.ifrn.conta.domain.ContaDebito;
import br.edu.ifrn.conta.domain.ContaPatrimonio;
import br.edu.ifrn.conta.domain.Dono;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class LancamentoRepositoryIT {

	@Autowired
	private LancamentoFactory lancamentoFactory;

	@Autowired
	private DonoFactory donoFactory;

	@Autowired
	private ContaCreditoFactory contaCreditoFactory;

	@Autowired
	private ContaDebitoFactory contaDebitoFactory;

	@Autowired
	private ContaPatrimonioFactory contaPatrimonioFactory;

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@BeforeEach
	public void deletarTodos() {
		this.lancamentoRepository.deleteAll();
		assertThat(this.lancamentoRepository.findAll())
			.isEmpty();
	}

	@Test
	public void repositorioNaoEhNulo() {
		assertThat(this.lancamentoRepository)
			.isNotNull();
	}

	@Test
	public void saldoCreditos300() {
            // cria o ambiente de teste
            Dono papai = this.donoFactory.papai();

            ContaPatrimonio poupanca
                    = this.contaPatrimonioFactory.poupanca();

            ContaCredito estagio
                    = this.contaCreditoFactory.estagio();

            this.lancamentoFactory.lancamento(papai, poupanca, estagio, new BigDecimal(300));

            // executa a operacao a ser testada
            // verifica o efeito da execucao da operacao a ser testada
            assertThat(this.lancamentoRepository.creditosSum(papai, poupanca).getValor())
                    .isEqualTo(new BigDecimal(300).setScale(2));
	}

	@Test
	public void saldoDebitos500() {
		// cria o ambiente de teste
		Dono papai = this.donoFactory.papai();

		ContaPatrimonio poupanca
			= this.contaPatrimonioFactory.poupanca();

		ContaDebito gasolina
			= this.contaDebitoFactory.gasolina();

		this.lancamentoFactory.lancamento(papai, gasolina, poupanca, new BigDecimal(500));

		// executa a operacao a ser testada
		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.lancamentoRepository.debitosSum(papai, poupanca).getValor())
			.isEqualTo(new BigDecimal(500).setScale(2));
	}
}
