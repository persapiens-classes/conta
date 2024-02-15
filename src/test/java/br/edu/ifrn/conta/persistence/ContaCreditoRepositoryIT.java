package br.edu.ifrn.conta.persistence;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.domain.Categoria;
import br.edu.ifrn.conta.domain.ContaCredito;
import static br.edu.ifrn.conta.util.CategoriaConstants.SALARIO;
import static br.edu.ifrn.conta.util.ContaCreditoConstants.ESTAGIO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContaCreditoRepositoryIT {

	@Autowired
	private ContaCreditoRepository contaCreditoRepository;

	@Autowired
	private ContaCreditoFactory contaCreditoFactory;

	@Test
	public void repositorioNaoEhNulo() {
		assertThat(this.contaCreditoRepository).isNotNull();
	}

	@Test
	public void findOneByExample() {
		// cria o ambiente de teste
		ContaCredito contaCredito = this.contaCreditoFactory.estagio();

		ContaCredito contaCreditoExemplo = ContaCredito.builder()
			.descricao(ESTAGIO)
			.categoria(Categoria.builder().descricao(SALARIO).build())
			.build();

		// executa a operacao a ser testada
		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.contaCreditoRepository.findOne(Example.of(contaCreditoExemplo)).get()).isEqualTo(contaCredito);
	}

}
