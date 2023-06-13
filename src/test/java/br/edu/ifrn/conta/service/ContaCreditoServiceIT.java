package br.edu.ifrn.conta.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.domain.ContaCredito;
import br.edu.ifrn.conta.persistence.CategoriaFactory;
import br.edu.ifrn.conta.persistence.ContaCreditoFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ContaCreditoServiceIT {

	@Autowired
	private ContaCreditoService contaCreditoServico;

	@Autowired
	private ContaCreditoFactory contaCreditoFactory;

	@Autowired
	private CategoriaFactory categoriaFactory;

	@Test
	public void repositorioNaoEhNulo() {
		assertThat(this.contaCreditoServico).isNotNull();
	}

	@Test
	public void salvarUm() {
		// cria o ambiente de teste
		ContaCredito contaCredito = this.contaCreditoFactory.receitaComConjuge();

		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.contaCreditoServico.findById(contaCredito.getId()).get())
			.isEqualTo(contaCredito);
	}

	@Test
	public void deletarUm() {
		// cria o ambiente de teste
		ContaCredito contaCredito = this.contaCreditoFactory.contaCredito(
			"CONTA ÚNICA DO SERVICO IT", this.categoriaFactory.banco());

		// executa a operacao a ser testada
		this.contaCreditoServico.delete(contaCredito);

		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.contaCreditoServico.findById(contaCredito.getId()).isPresent())
			.isFalse();
	}

}
