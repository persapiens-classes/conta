package br.edu.ifrn.conta.persistence;

import br.edu.ifrn.conta.ContaApplication;
import br.edu.ifrn.conta.domain.Categoria;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ContaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CategoriaRepositoryIT {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private CategoriaFactory categoriaFactory;

	@Test
	public void repositorioNaoEhNulo() {
		assertThat(this.categoriaRepository)
			.isNotNull();
	}

	@Test
	public void deletarUm() {
		// cria o ambiente de teste
		Categoria categoria = this.categoriaFactory.categoria("CATEGORIA UNICA");

		// executa a operacao a ser testada
		this.categoriaRepository.delete(categoria);

		// verifica o efeito da execucao da operacao a ser testada
		assertThat(this.categoriaRepository.findById(categoria.getId()).isPresent())
			.isFalse();
	}

	@Test
	public void salvarUm() {
		// executa a operacao a ser testada
		Categoria categoria = this.categoriaFactory.transporte();

		// verifica o efeito da execucao da operacao a ser testada
		assertThat(categoria.getId())
			.isNotNull();
	}

}
