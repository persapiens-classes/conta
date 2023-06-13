package br.edu.ifrn.conta.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ifrn.conta.domain.Categoria;

@Component
public class CategoriaFactory {

	public final static String SALARIO = "Salário";
	public final static String TRANSPORTE = "Transporte";
	public final static String BANCO = "Banco";
	public final static String CATEGORIA_DESPESA_CONJUGE = "categoria despesa com cônjuge";
	public final static String CATEGORIA_RECEITA_CONJUGE = "categoria receita com cônjuge";

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria categoria(String descricao) {
		Categoria categoria = this.categoriaRepository.findByDescricao(descricao);
		if (categoria == null) {
			categoria = Categoria.builder().descricao(descricao).build();
			this.categoriaRepository.save(categoria);
		}
		return categoria;
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

	public Categoria categoriaReceitaConjuge() {
		return categoria(CATEGORIA_RECEITA_CONJUGE);
	}

	public Categoria categoriaDespesaConjuge() {
		return categoria(CATEGORIA_DESPESA_CONJUGE);
	}
}
