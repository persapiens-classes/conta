package br.edu.ifrn.conta.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifrn.conta.domain.Categoria;
import static br.edu.ifrn.conta.util.CategoriaConstants.BANCO;
import static br.edu.ifrn.conta.util.CategoriaConstants.ESPECIE;
import static br.edu.ifrn.conta.util.CategoriaConstants.SALARIO;
import static br.edu.ifrn.conta.util.CategoriaConstants.TRANSPORTE;
import java.util.Optional;

@Component
public class CategoriaFactory {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria categoria(String descricao) {
		Optional<Categoria> findByDescricao = this.categoriaRepository.findByDescricao(descricao);
		if (findByDescricao.isEmpty()) {
			Categoria categoria = Categoria.builder().descricao(descricao).build();
			return this.categoriaRepository.save(categoria);
		}
		else {
			return findByDescricao.get();
		}
	}

	public Categoria transporte() {
		return categoria(TRANSPORTE);
	}

	public Categoria banco() {
		return categoria(BANCO);
	}

	public Categoria salario() {
		return categoria(SALARIO);
	}

	public Categoria especie() {
		return categoria(ESPECIE);
	}

}
