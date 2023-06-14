package br.edu.ifrn.conta.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.domain.Categoria;
import br.edu.ifrn.conta.persistence.CategoriaFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoriaServiceIT {

	@Autowired
	private CategoriaService categoriaServico;

	@Autowired
	private CategoriaFactory categoriaFactory;

	@Test
	public void repositorioNaoEhNulo() {
		assertThat(this.categoriaServico).isNotNull();
	}

	@Test
	public void salvarUm() {
		// cria o ambiente de teste
		Categoria categoria = this.categoriaFactory.banco();

		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.categoriaServico.findById(categoria.getId()).get())
			.isEqualTo(categoria);
	}

	@Test
	public void deletarUm() {
		// cria o ambiente de teste
		Categoria categoria = this.categoriaFactory.categoria("CATEGORIA UNICA DO SERVICOIT");

		// executa a operacao a ser testada
		this.categoriaServico.delete(categoria);

		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.categoriaServico.findById(categoria.getId()).isPresent())
			.isFalse();
	}

}
