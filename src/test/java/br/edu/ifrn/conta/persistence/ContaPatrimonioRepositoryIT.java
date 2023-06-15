package br.edu.ifrn.conta.persistence;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.domain.Categoria;
import br.edu.ifrn.conta.domain.ContaPatrimonio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContaPatrimonioRepositoryIT {

	@Autowired
	private ContaPatrimonioFactory contaPatrimonioFactory;

	@Autowired
	private ContaPatrimonioRepository contaPatrimonioRepository;

	@Test
	public void repositorioNaoEhNulo() {
		assertThat(this.contaPatrimonioRepository).isNotNull();
	}

	@Test
	public void findAllByExample() {
		// cria o ambiente de teste
		this.contaPatrimonioFactory.poupanca();

		ContaPatrimonio contaPatrimonioExemplo = ContaPatrimonio.builder()
			.categoria(Categoria.builder().descricao(CategoriaFactory.BANCO).build())
			.build();

		// executa a operacao a ser testada
		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.contaPatrimonioRepository.count(Example.of(contaPatrimonioExemplo)))
			.isEqualTo(1);
	}

}
