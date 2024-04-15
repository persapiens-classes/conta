package br.edu.ifrn.conta.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.domain.ContaCredito;
import br.edu.ifrn.conta.domain.ContaDebito;
import br.edu.ifrn.conta.domain.ContaPatrimonio;
import br.edu.ifrn.conta.domain.Dono;
import br.edu.ifrn.conta.persistence.ContaCreditoFactory;
import br.edu.ifrn.conta.persistence.ContaDebitoFactory;
import br.edu.ifrn.conta.persistence.ContaPatrimonioFactory;
import br.edu.ifrn.conta.persistence.DonoFactory;
import br.edu.ifrn.conta.persistence.LancamentoFactory;
import br.edu.ifrn.conta.persistence.ValorInicialDoDonoNaContaPatrimonioFactory;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SaldoServiceIT {

	@Autowired
	private LancamentoFactory lancamentoFactory;

	@Autowired
	private DonoFactory donoFactory;

	@Autowired
	private SaldoService saldoService;

	@Autowired
	private ValorInicialDoDonoNaContaPatrimonioFactory valorInicialDoDonoNaContaPatrimonioFactory;

	@Autowired
	private ContaPatrimonioFactory contaPatrimonioFactory;

	@Autowired
	private ContaCreditoFactory contaCreditoFactory;

	@Autowired
	private ContaDebitoFactory contaDebitoFactory;

	@Test
	public void saldo800() {
		// cria o ambiente de teste
		Dono papai = this.donoFactory.papai();

		ContaPatrimonio carteira = this.contaPatrimonioFactory.carteira();

		ContaDebito gasolina = this.contaDebitoFactory.gasolina();

		ContaCredito estagio = this.contaCreditoFactory.estagio();

		this.valorInicialDoDonoNaContaPatrimonioFactory.valorInicialDoDonoNaContaPatrimonio(papai, carteira,
				new BigDecimal(1000));

		this.lancamentoFactory.lancamento(papai, gasolina, carteira, new BigDecimal(500));
		this.lancamentoFactory.lancamento(papai, carteira, estagio, new BigDecimal(300));

		// executa a operacao a ser testada
		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.saldoService.saldo(papai, carteira)).isEqualTo(new BigDecimal(800).setScale(2));
	}

}
