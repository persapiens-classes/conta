package br.edu.ifrn.conta.persistence;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.domain.Categoria;
import br.edu.ifrn.conta.domain.ContaDebito;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContaDebitoRepositoryIT {

	@Autowired
	private ContaDebitoFactory contaDebitoFactory;

	@Autowired
	private ContaDebitoRepository contaDebitoRepository;

	@Test
	public void repositorioNaoEhNulo() {
		assertThat(this.contaDebitoRepository)
			.isNotNull();
	}

	@Test
	public void findAllByExample() {
		// cria o ambiente de teste
		ContaDebito contaDebito = this.contaDebitoFactory.gasolina();

		ContaDebito contaDebitoExemplo = ContaDebito.builder()
			.categoria(Categoria.builder().descricao(CategoriaFactory.TRANSPORTE).build())
			.build();

		// executa a operacao a ser testada
		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.contaDebitoRepository.findOne(Example.of(contaDebitoExemplo)).get())
			.isEqualTo(contaDebito);
	}

}
